package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;
   
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    
    public Message createMessage(Message message){
        AccountService as = new AccountService();
        if(message.getMessage_text().length() > 255){ //less than 255 characters
            return null;
        } else if (message.getMessage_text().length() == 0){ //not blank
            return null;
        } else if(as.getAccountByID(message.getPosted_by()) == null){ //poster exists
            return null;
        }
        return messageDAO.insertMessage(message);
    }

    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(int message_id){
        if(messageDAO.getMessageByID(message_id) != null){
            Message message = messageDAO.getMessageByID(message_id);
            messageDAO.deleteMessageByID(message_id);
            return message;
        }
        return null;
    }

    public Message updateMessage(int id, Message message){
        if (messageDAO.getMessageByID(id) == null){
            return null;
        } else if (message.getMessage_text().length() > 255){
            return null;
        } else if (message.getMessage_text().length() == 0){
            return null;
        }
        messageDAO.updateMessage(id, message);;

        return messageDAO.getMessageByID(id);
    }

    public List<Message> getMessagesByAccountID(int account_id){
        return messageDAO.getMessageByAccountID(account_id);
    }
}
