import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BankStatement extends Transaction{

 //  public static void main(String[] args) {

   //    BankStatement bs = new BankStatement();
    //   bs.setStatementPeriod(9,2020);
//        bs.initialThisMonthStatement();
//        bs.containsThisTxnAmount(232.00);
  // }

    ArrayList<Transaction> bankRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledRecords = new ArrayList<>();

    Calendar statementStartDate = Calendar.getInstance();
    Calendar statementEndDate = Calendar.getInstance();
    String statementPeriod = null;

    BankStatement(){

    }

    String setStatementPeriod(int mth, int year){

        statementPeriod = mth + "-" + year;
        statementStartDate.set(year,mth-1,01,0,0,0);
        statementEndDate.set(year,mth-1,statementStartDate.getActualMaximum(Calendar.DATE),0,0,0);
        System.out.println("Statement period is set starting "+ statementStartDate.getTime()+ " TO "+ statementEndDate.getTime());

        return statementPeriod;
    }

    void initialThisMonthStatement(){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.add(new Transaction(260.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"02/09/2020"));
        bankRecords.add(new Transaction(200.00,"01/09/2020"));
        bankRecords.add(new Transaction(814.45,"01/09/2020"));

        System.out.println("Total records in Bank Statement Records:" + bankRecords.size());

    }
    Boolean hasBankRecords(){
        return bankRecords.size()>0;
    }

    Boolean hasBankRecordWithAmt(double bankAmt){

        for (Transaction bankRecord : bankRecords) {
            if (bankRecord.transactionAmount == bankAmt) {
                reconciledRecords.add(new Transaction(bankAmt, bankRecord.transactionDate));
                System.out.println("Reconciled record count:"+reconciledRecords.size());
                System.out.println("Record date:"+bankRecord.transactionDate+ " with Amount:"+ bankRecord.transactionAmount);
            }
        }

       return reconciledRecords.size()>0;

    }


}
