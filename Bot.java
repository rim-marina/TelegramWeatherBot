import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }

    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);                                       //включить возможность разметки
        sendMessage.setChatId(message.getChatId().toString());                 //установить id отправителя
        sendMessage.setReplyToMessageId(message.getMessageId());                //установили кому будем отвечать
        sendMessage.setText(text);                                  //установим текст, который отправляли в этот метод
        try {
            setButton(sendMessage);         //устанавливаем клавиатуру
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /* для приема сообщений*/
    @Override
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch (message.getText()){
                case "/помощь":
                    sendMsg(message, "Чтобы узнать погоду введите название города");
                    break;
                case "/start":
                    sendMsg(message, "Вас приветствует прогноз погоды!");
                    break;
                case "/связь с администратором":
                    sendMsg(message, "Это легкая программа! Не дергайте администратора по пустякам");
                    break;
                default:
                    try{
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    }catch (IOException e){
                        sendMsg(message, " Такой город не найден");
                    }
            }
        }
    }

    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();        //создаем клавиатуру
        sendMessage.setReplyMarkup(replyKeyboardMarkup);    //установить разметку клавиатуры, связать сообщение с клавиатурой
        replyKeyboardMarkup.setSelective(true);    //параметр, выводящий клавиатуру определенным user или всем
        replyKeyboardMarkup.setResizeKeyboard(true);    //подгонка клавы под количество кнопок
        replyKeyboardMarkup.setOneTimeKeyboard(false);      //скрывать ли клаву после нажатия кнопки

        List<KeyboardRow> keyboardRowList = new ArrayList<>();  //создание листа клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();       //создание первой строчки клавы
        keyboardFirstRow.add(new KeyboardButton("/помощь"));  //добавляем кнопку
        keyboardFirstRow.add(new KeyboardButton("/связь с администратором"));

        keyboardRowList.add(keyboardFirstRow);      //добавляем строчку кнопок в список
        replyKeyboardMarkup.setKeyboard(keyboardRowList);    //устанавливаем список на клавиатуре
    }

    @Override
    public String getBotUsername() {return "Try_17Bot";}

    @Override
    public String getBotToken() {return "1721703212:AAHzgEh1B7EcT1vYvNd05X2_5s3iFdOxhsw";}

}
