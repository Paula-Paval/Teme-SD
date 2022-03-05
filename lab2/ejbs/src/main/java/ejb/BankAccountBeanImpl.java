package ejb;

import interfaces.BankAccountBeanRemote;

import java.io.Serializable;

public class BankAccountBeanImpl  implements BankAccountBeanRemote, Serializable {

    private Integer avaibleAmount=0;

    public Boolean withdraw(Integer amount) {
        if(avaibleAmount>=amount)
        {
            avaibleAmount-=amount;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void deposit(Integer amount) {
        avaibleAmount+=amount;
    }

    public Integer getBalance() {
        return avaibleAmount;
    }
}
