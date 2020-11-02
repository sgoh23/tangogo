import java.util.ArrayList;

public class BankStatement {

//    public static void main(String[] args) {
//        BankStatement bs = new BankStatement();
//        bs.initialThisMonthStatement();
//        bs.containsThisTxnAmount(232.00);
//    }

    ArrayList<Transaction> bankRecords = new ArrayList<>();

    BankStatement(){

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

    Boolean containsThisTxnAmount(double bankAmt){
        int countMatches = 0;
        for (Transaction bankRecord : bankRecords) {
            if (bankRecord.transactionAmount == bankAmt) {
                countMatches++;
                System.out.println("Record date:"+bankRecord.transactionDate+ " with Amount:"+ bankRecord.transactionAmount);
            }
        }

       return countMatches > 0;

    }

    void clearThisMonthRecords(){

        bankRecords.clear();

    }
}
