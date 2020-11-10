import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class BankStatement extends Transaction{

    private static ArrayList<Transaction> bankRecords = new ArrayList<>();
    private ArrayList<Transaction> unreconciledRecords = new ArrayList<>();
    private ArrayList<Transaction> reconciledRecords = new ArrayList<>();

    Calendar statementStartDate = Calendar.getInstance();
    Calendar statementEndDate = Calendar.getInstance();
    String statementPeriod = null;
    private double amt;

    public static void main(String[] args) {

        BankStatement bs = new BankStatement();
        bs.setStatementPeriod(9,2020);
        bs.initialThisMonthStatement(bs.statementPeriod);
        int i = bs.findUnreconciledBankRecordWithAmt(232.00);
//        System.out.println("UPDATED RECORDS #####################");
//        System.out.println("Total records in Bank Statement Records:" + bs.bankRecords.size());
//        System.out.println("Total records in unRecon Records:" + bs.unreconciledRecords.size());
//        System.out.println("Total records in Recon Records:" + bs.reconciledRecords.size());
//        System.out.println("END UPDATE#####################");
        bs.printBankRecord();

    }
    BankStatement(){

    }

    BankStatement(String period){

        int month = Integer.parseInt(period.substring(0,period.indexOf("-"))); //to get month and convert to int;
        int year = Integer.parseInt(period.substring(period.indexOf("-")+1)); //to get year and convert to int;
        System.out.println("Bank Statement for Month:"+month);
        System.out.println("Bank Statement for Year:"+year);
        setStatementPeriod(month,year);

    }


    String setStatementPeriod(int mth, int year){

        statementPeriod = mth + "-" + year;
        statementStartDate.set(year,mth-1,01,0,0,0);
        statementEndDate.set(year,mth-1,statementStartDate.getActualMaximum(Calendar.DATE),0,0,0);
        System.out.println("Statement period is set starting "+ statementStartDate.getTime()+ " TO "+ statementEndDate.getTime());

        return statementPeriod;
    }

    String getStatementPeriod(){
        return statementPeriod;
    }

    int initialThisMonthStatement(String period){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.clear();
        bankRecords.add(new Transaction(260.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"02/09/2020"));
        bankRecords.add(new Transaction(200.00,"01/09/2020"));
        bankRecords.add(new Transaction(814.45,"01/09/2020"));

        initialUnreconciledRecords();

        System.out.println("+++++++++++++++++++INITIAL BANK STATEMENT RUN FOR "+period+"+++++++++++++++++++");
        System.out.println("Total records in Bank Statement Records:" + bankRecords.size());
        System.out.println("Total records in unRecon Records:" + unreconciledRecords.size());
        System.out.println("Total records in Recon Records:" + reconciledRecords.size());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        return bankRecords.size();
    }

    void initialUnreconciledRecords(){
        unreconciledRecords =  (ArrayList<Transaction>) bankRecords.clone();

    };

    int getUnreconciledRecordsSize(){
        return unreconciledRecords.size();
    }

    int getReconciledRecordSize(){
        return reconciledRecords.size();
    }
    int getBankRecordsSize(){
        return bankRecords.size();
    }

    int findUnreconciledBankRecordWithAmt(double bankAmt){

        int recordIndex = 0;
        boolean updateReconRecords = false;

        try{
            for (Transaction record : unreconciledRecords) {
                if (record.transactionAmount == bankAmt) {
                    System.out.println("Update recon For BankAmt: "+bankAmt+"...Found in record:"+unreconciledRecords.get(recordIndex).transactionAmount+" Dated: "+unreconciledRecords.get(recordIndex).transactionDate);
                    updateReconRecords = setReconcileRecord(record);
                    break;
                }
                recordIndex++;
            }


        }catch(Exception e){
            System.out.println(e);
        }
        return recordIndex;

    }

    Boolean setReconcileRecord(Transaction txn){
        boolean recon = false;
        reconciledRecords.add(new Transaction(txn.transactionAmount,txn.transactionDate));
        removeFromUnreconciledList(txn.transactionAmount);

        if(unreconciledRecords.size() == bankRecords.size()-reconciledRecords.size()){
            recon = true;
        }
        return true;
    }

    void removeFromUnreconciledList(double amt) {

        try {

            int index = 0;

            for (int i = 0; i < unreconciledRecords.size(); i++) {
                if (unreconciledRecords.get(i).transactionAmount == amt) {
                    index = i;
                    break; //reconcile the first record
                }
            }

            unreconciledRecords.remove(index);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    void printBankRecord(){
        int i=0;

        System.out.println("||||||||| PRINTING BANK RECORDS |||||||||");

        for (Transaction record : bankRecords) {
            System.out.println(bankRecords.get(i).transactionAmount);
            i++;
        }

    }



}
