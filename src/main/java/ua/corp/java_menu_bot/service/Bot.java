package ua.corp.java_menu_bot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
//повертає імя боту
    @Override
    public String getBotUsername() {
        return "menuByLjurBot";
    }
//унікальний пароль який генерує телеграм до цього боту
    @Override
    public String getBotToken() {
        return "6335038035:AAEKg05dzkfCjOuerx6ZXqSTLsr1FnKLPLI";
    }
//тут оброблятимуться запити користувача. На вхід щось від користувача
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println("Message received " + message.getText());
        SendMessage sendMessage =new SendMessage();
        sendMessage.setText("Hello user! I received your message " + message.getText());
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        if(message.getText().equals("/start")) {
        String text = "Welcome to Recipe bot! Please pass the meal of the day!\n";


        sendMessage.enableMarkdown(true);
        ReplyKeyboardMarkup keyboardMarkup = getMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.setText(text);
        }
        if(message.getText().equals("breakfast")){
            String menu = "Breakfast menu! \n";
            menu = menu + "1. Blueberry_Banana-Nut Smoothie \n";
            menu = menu + "2. Classic Omelet and Greens \n";
            menu = menu + "3. Curry_Avocado Crispy Egg Toast \n";
            sendMessage.setText(menu);
        }
        if(message.getText().equals("dinner")){
            String menu = "Dinner menu! \n";
            menu = menu + "1. Creamy Lemon Chicken Pasta \n";
            menu = menu + "2. Turkey Tacos \n";
            menu = menu + "3. Vegetarian Lasagna \n";
            sendMessage.setText(menu);
        }
        if(message.getText().equals("lunch")){
            String menu = "Lunch menu is in progress...";

            sendMessage.setText(menu);
        }
        if(message.getText().equals("supper")){
            String menu = "Supper menu is in progress...";

            sendMessage.setText(menu);
        }


        //відправляю повідомлення
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }
    private ReplyKeyboardMarkup getMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("breakfast");
        keyboardRow.add("dinner");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("lunch");
        keyboardSecondRow.add("supper");
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
