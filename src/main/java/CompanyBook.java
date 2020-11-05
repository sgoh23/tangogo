import java.util.ArrayList;

public class CompanyBook {

    ArrayList<Transaction> PaymentRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledRecords = new ArrayList<>();

    void setBeginningBalance(){
        PaymentRecords.add(new Transaction(232.00,"01/09/2020"));
    }

    Boolean hasTransactionWithAmt(double amt){
        setBeginningBalance();
        for (Transaction paymentRecord : PaymentRecords) {

            if (paymentRecord.transactionAmount == amt) {
                reconciledRecords.add(new Transaction(amt, paymentRecord.transactionDate));
                System.out.println("Reconciled record count:"+reconciledRecords.size());
            }

        }
        return reconciledRecords.size()>0;
    }

}
