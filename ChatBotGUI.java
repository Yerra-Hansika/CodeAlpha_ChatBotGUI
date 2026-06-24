import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatBotGUI {

    private static String getResponse(String input) {

        input = input.toLowerCase();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you?";
        }

        else if (input.contains("name")) {
            return "I am an AI Chatbot.";
        }

        else if (input.contains("how are you")) {
            return "I am doing great!";
        }

        else if (input.contains("java")) {
            return "Java is a popular programming language.";
        }

        else if (input.contains("college")) {
            return "College is a place for learning and growth.";
        }

        else if (input.contains("bye")) {
            return "Goodbye! Have a nice day.";
        }

        else {
            return "Sorry, I don't understand that question.";
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("AI Chatbot");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        JTextField inputField = new JTextField();

        JButton sendButton = new JButton("Send");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String userInput = inputField.getText();

                chatArea.append("You: " + userInput + "\n");

                String response = getResponse(userInput);

                chatArea.append("Bot: " + response + "\n\n");

                inputField.setText("");
            }
        });

        frame.setVisible(true);
    }
}