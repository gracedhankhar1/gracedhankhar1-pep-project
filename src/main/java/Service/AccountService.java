package Service;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    private AccountDAO accountDAO;
   
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    //Get all Accounts
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    //Register New Account
    public Account addAccount(Account account) {
        if(account.getPassword().length() < 4){
            return null;
        } else if (account.getUsername().length() == 0){
            return null;
        } else if (accountDAO.getAllAccounts().contains(account)){
            return null;
        }
        return accountDAO.insertAccount(account);
    }
    
    public Account getAccountByID(int id){
        return accountDAO.getAccountByID(id);
    }

    public Account loginAccount(Account account){
        if(accountDAO.getAccountByUsername(account.getUsername()) == null){
            return null;
        } else if (!accountDAO.getAccountByUsername(account.getUsername()).getPassword().equals(account.getPassword())){
            return null;
        }
        return accountDAO.getAccountByUsername(account.getUsername());
    }

}
