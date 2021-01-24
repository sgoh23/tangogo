package com.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankStatement extends Transaction{

    ArrayList<Transaction> bankRecords = new ArrayList<>();
    ArrayList<Transaction> GIROCharges = new ArrayList<>();

    public Calendar statementStartDate = Calendar.getInstance();
    public Calendar statementEndDate = Calendar.getInstance();
    public String statementPeriod = null;
    public double statementClosingBalance;
    public double statementOpeningBalance;
    public double totalCreditAmount;
    public double totalDebitAmount;

    public BankStatement(){

    }

    public BankStatement(String period){

        int month = Integer.parseInt(period.substring(0,period.indexOf("-"))); //to get month and convert to int;
        int year = Integer.parseInt(period.substring(period.indexOf("-")+1)); //to get year and convert to int;
        statementPeriod = setStatementPeriod(month,year);
        initiateThisMonthStatement(statementPeriod);
    }


    public String setStatementPeriod(int mth, int year){

        statementPeriod = mth + "-" + year;
        statementStartDate.set(year,mth-1,1,0,0,0);
        statementEndDate.set(year,mth-1,statementStartDate.getActualMaximum(Calendar.DATE),0,0,0);

        return statementPeriod;
    }

    public String getStatementPeriod(){
        return statementPeriod;
    }

    public int initiateMockRecords(){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.clear();
        bankRecords.add(new Transaction(230.00,"31/08/2020"));
        bankRecords.add(new Transaction(230.00,"01/09/2020"));
        bankRecords.add(new Transaction(230.00,"02/09/2020"));
        bankRecords.add(new Transaction(360.00,"02/09/2020"));
        bankRecords.add(new Transaction(400.00,"02/09/2020"));
        bankRecords.add(new Transaction(-100.00,"02/09/2020"));

        getBankRecordsBalance();
        return bankRecords.size();
    }

    public int initiateThisMonthStatement(String period){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.clear();

        //Mock data
        //initiateMockRecords();

        //Test data
        initiateTestRecords();

        //Real data
        //to be coded..

        statementClosingBalance = +statementOpeningBalance+getBankRecordsBalance();
        return bankRecords.size();
    }

    public int getBankRecordsSize(){
        return bankRecords.size();
    }
    public ArrayList<Transaction> getBankRecords(){
       return bankRecords;
    }

    public Map<String, Double> getChargesSumByDate(String categoryname){

        List<Transaction> gclist = this.bankRecords.stream()
                .filter(t -> t.transactionChannel.equals(categoryname))
                .collect(Collectors.toList());

        Map<String, Double> sumByDate = gclist.stream().collect(
                Collectors.groupingBy(Transaction::getTransactionDate,
                        Collectors.summingDouble(Transaction::getTransactionAmount)));

        return sumByDate;
    }

    public void initiateTestRecords(){

        statementOpeningBalance= 312662.74;
        bankRecords.add(new Transaction(260,"01/09/2020","from MUHAMMAD ZUHAI | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MUHAMMAD ZUHAI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(232,"01/09/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN fbp1371p | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"01/09/2020","MUHAMMAD HIDAYAT BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HIDAYAT BI via PayNow: FBQ8470K | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HIDAYAT BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(814.45,"01/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 31/08/2020 | POS SETTLEMENT","ATAN MOTOR","31/08/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(265,"01/09/2020","MOHAMED FEROZE BIN | PAYMENT/TRANSFER IHRP MOHAMED FEROZE BIN Feroze  FBN 401D | IHRP | PAYMENT/TRANSFER","MOHAMED FEROZE BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(333,"01/09/2020","SUHAIRY BIN FADLILA | PAYMENT/TRANSFER OTHR S$ SUHAIRY BIN FADLILA via PayNow: FBR3022Z | OTHR S$ | PAYMENT/TRANSFER","SUHAIRY BIN FADLILA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(232,"01/09/2020","SURESH PILLAI SAMMU | PAYMENT/TRANSFER OTHR SURESH PILLAI SAMMU Transfer | OTHR | PAYMENT/TRANSFER","SURESH PILLAI SAMMU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(278,"01/09/2020","HO ZHI QUAN | PAYMENT/TRANSFER OTHR HO ZHI QUAN FBK9357E | OTHR | PAYMENT/TRANSFER","HO ZHI QUAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(20000,"01/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(7250,"01/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(5090,"01/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(100,"01/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(2.26,"01/09/2020","from # TAN HWA MENG | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from # TAN HWA MENG","","FUND TRANSFER"));
        bankRecords.add(new Transaction(307,"01/09/2020","SENG TSHUN MING | PAYMENT/TRANSFER LOAN SENG TSHUN MING Fbm1996c | LOAN | PAYMENT/TRANSFER","SENG TSHUN MING","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-42.8,"01/09/2020","P200901 | FUND TRANSFER Fund Transfer to account 525782041001 SGD 42.80@1 Atan Motoring Supply Pte Ltd Invoice: 20006736 | CT0019431506 | FUND TRANSFER","P200901","CT0019431506","FUND TRANSFER"));
        bankRecords.add(new Transaction(500,"01/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(313,"01/09/2020","SHAKYNA BINTE RAMLE | PAYMENT/TRANSFER OTHR SHAKYNA BINTE RAMLE FBN2823G NTUC insurance | OTHR | PAYMENT/TRANSFER","SHAKYNA BINTE RAMLE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"01/09/2020","MOHAMMAD ZAMEER BIN | PAYMENT/TRANSFER OTHR S$ MOHAMMAD ZAMEER BIN via PayNow: FBJ6122R | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD ZAMEER BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(12000,"01/09/2020","TAN KAR LOKE KEVIN | PAYMENT/TRANSFER OTHR S$ TAN KAR LOKE KEVIN via PayNow: Cbf190 | OTHR S$ | PAYMENT/TRANSFER","TAN KAR LOKE KEVIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"01/09/2020","CHAN CHEE HONG | PAYMENT/TRANSFER LOAN CHAN CHEE HONG FBR2603D | LOAN | PAYMENT/TRANSFER","CHAN CHEE HONG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(239,"01/09/2020","SUNDARESWARAR S/O P | PAYMENT/TRANSFER OTHR S$ SUNDARESWARAR S/O P via PayNow: Fx1098P payment | OTHR S$ | PAYMENT/TRANSFER","SUNDARESWARAR S/O P","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(198,"01/09/2020","NOHAZLAN BIN ALI | SI               FBD6525T IBG GIRO | OTHR | IBG GIRO","NOHAZLAN BIN ALI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(208,"01/09/2020","MOHD HAMSANI B HAMD | SI               FBP6035C IBG GIRO | OTHR | IBG GIRO","MOHD HAMSANI B HAMD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(281,"01/09/2020","CHEW KEAN NAM | SI               FBR5128P IBG GIRO | OTHR | IBG GIRO","CHEW KEAN NAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(232,"01/09/2020","SHAKYNA BINTE RAMLE | PAYMENT/TRANSFER OTHR SHAKYNA BINTE RAMLE FBN2823G | OTHR | PAYMENT/TRANSFER","SHAKYNA BINTE RAMLE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(435,"01/09/2020","LUI WAI LEONG | PAYMENT/TRANSFER OTHR S$ LUI WAI LEONG via PayNow: FBQ7317C | OTHR S$ | PAYMENT/TRANSFER","LUI WAI LEONG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(221,"01/09/2020","OOI WOOI HIM | PAYMENT/TRANSFER OTHR S$ OOI WOOI HIM via PayNow: SEP Installment | OTHR S$ | PAYMENT/TRANSFER","OOI WOOI HIM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"01/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-7753,"01/09/2020","186290 | CHQ186290 CHEQUE |  | CHEQUE","186290","","CHEQUE"));
        bankRecords.add(new Transaction(-7753,"01/09/2020","186291 | CHQ186291 CHEQUE |  | CHEQUE","186291","","CHEQUE"));
        bankRecords.add(new Transaction(218,"02/09/2020","MOHD RAZALI BIN MOH | PAYMENT/TRANSFER OTHR MOHD RAZALI BIN MOH FBQ4745U 83210310 | OTHR | PAYMENT/TRANSFER","MOHD RAZALI BIN MOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(251,"02/09/2020","from FBL3296G FARID | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBL3296G FARID","","FUND TRANSFER"));
        bankRecords.add(new Transaction(438,"02/09/2020","HO CHI WING | PAYMENT/TRANSFER OTHR HO CHI WING FBG0332G | OTHR | PAYMENT/TRANSFER","HO CHI WING","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"02/09/2020","MOHAMED AJMEL BIN M | PAYMENT/TRANSFER OTHR MOHAMED AJMEL BIN M Ajmel fba2060j | OTHR | PAYMENT/TRANSFER","MOHAMED AJMEL BIN M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(160,"02/09/2020","HELMI HUSAINI BIN S | PAYMENT/TRANSFER OTHR S$ HELMI HUSAINI BIN S via PayNow: FBK7313U | OTHR S$ | PAYMENT/TRANSFER","HELMI HUSAINI BIN S","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(222.18,"02/09/2020","MOHAMMAD KHAIRUL AZ | PAYMENT/TRANSFER OTHR S$ MOHAMMAD KHAIRUL AZ via PayNow: FBP6279R | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD KHAIRUL AZ","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(340,"02/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 01/09/2020 | POS SETTLEMENT","ATAN MOTOR","01/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-25,"02/09/2020","29/08/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","29/08/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(130,"02/09/2020","from FBP1105S JAYA | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBP1105S JAYA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(235,"02/09/2020","LEE KAH KUANG | PAYMENT/TRANSFER LOAN LEE KAH KUANG FBQ5352M | LOAN | PAYMENT/TRANSFER","LEE KAH KUANG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-952,"02/09/2020","186162 | CHQ186162 CASH CHQ WDL |  | CASH CHQ WDL","186162","","CASH CHQ WDL"));
        bankRecords.add(new Transaction(372,"02/09/2020","MOHAMAD NASRULLAH B | PAYMENT/TRANSFER OTHR S$ MOHAMAD NASRULLAH B via PayNow: FBA9420H | OTHR S$ | PAYMENT/TRANSFER","MOHAMAD NASRULLAH B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(208,"02/09/2020","MUHAMMAD SHUKOR BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SHUKOR BIN FBP9788T | OTHR | PAYMENT/TRANSFER","MUHAMMAD SHUKOR BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3237,"02/09/2020","LTA | LTAFBA4771C IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(264,"02/09/2020","MANAF BIN ABDULLAH | SI               FBJ6983Y IBG GIRO | OTHR | IBG GIRO","MANAF BIN ABDULLAH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(214,"02/09/2020","ONG BOON HUAT | SI               FBP4146D IBG GIRO | OTHR | IBG GIRO","ONG BOON HUAT","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(232,"02/09/2020","ARMAN BIN MOHAMED A | SI               FBN8463Y IBG GIRO | OTHR | IBG GIRO","ARMAN BIN MOHAMED A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(264,"02/09/2020","SIM LAI HOCK | SI               FBQ7119H IBG GIRO | OTHR | IBG GIRO","SIM LAI HOCK","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(198,"02/09/2020","SUFFIAN BIN MOHD NO | SI               FBJ1917Y IBG GIRO | OTHR | IBG GIRO","SUFFIAN BIN MOHD NO","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(520,"02/09/2020","JURAIMI BIN SAMRI | SI               FBL4574Y IBG GIRO | OTHR | IBG GIRO","JURAIMI BIN SAMRI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(166,"02/09/2020","NOR IRSHARUDDIN BIN | PAYMENT/TRANSFER OTHR NOR IRSHARUDDIN BIN FBC6854Z S9702411A | OTHR | PAYMENT/TRANSFER","NOR IRSHARUDDIN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-10683,"02/09/2020","186289 | CHQ186289 CHEQUE |  | CHEQUE","186289","","CHEQUE"));
        bankRecords.add(new Transaction(232.41,"02/09/2020","NURUL KHAIRUNNISA B | PAYMENT/TRANSFER OTHR NURUL KHAIRUNNISA B FBP994Z | OTHR | PAYMENT/TRANSFER","NURUL KHAIRUNNISA B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(611.71,"03/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 02/09/2020 | POS SETTLEMENT","ATAN MOTOR","02/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-25,"03/09/2020","31/08/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","31/08/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"03/09/2020","31/08/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","31/08/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(1263,"03/09/2020","MOM | SEC 198903552W-PTE-01 20081 GIRO | OTHR 262499020081 | GIRO","MOM","OTHR 262499020081","GIRO"));
        bankRecords.add(new Transaction(249,"03/09/2020","VIMAL DEVARAJAN | PAYMENT/TRANSFER OTHR S$ VIMAL DEVARAJAN via PayNow: FBQ719Y | OTHR S$ | PAYMENT/TRANSFER","VIMAL DEVARAJAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(395,"03/09/2020","from FBG6220G RIYAN | OTHR - FY9951T Insurance FUND TRANSFER |  | FUND TRANSFER","from FBG6220G RIYAN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(296,"03/09/2020","CHEAH WENG LEONG | PAYMENT/TRANSFER OTHR CHEAH WENG LEONG FBQ5269X | OTHR | PAYMENT/TRANSFER","CHEAH WENG LEONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(316.92,"03/09/2020","from Lyna SALINAH B | OTHR - FBK 5401L (Zahid) FUND TRANSFER |  | FUND TRANSFER","from Lyna SALINAH B","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-567.26,"03/09/2020","Keppel Electric Pte | CU00012269 IBG GIRO | COLL CU00012269 | IBG GIRO","Keppel Electric Pte","COLL CU00012269","IBG GIRO"));
        bankRecords.add(new Transaction(-42.04,"03/09/2020"," | xx-8024 POPULAR BOOK CO P/L      2 POS PURCHASE    NETS | POPULAR BO | POS PURCHASE    NETS","","POPULAR BO","POS PURCHASE    NETS"));
        bankRecords.add(new Transaction(2026,"03/09/2020","682618 | SCB (SG) CHQ682618 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","682618","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(171,"03/09/2020","MUHAMMAD HAIRULNIZA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIRULNIZA via PayNow: FBN2190Z. 947J | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIRULNIZA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(244,"04/09/2020","SHER WENYONG BENJAM | PAYMENT/TRANSFER OTHR SHER WENYONG BENJAM FBL9839B | OTHR | PAYMENT/TRANSFER","SHER WENYONG BENJAM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"04/09/2020","AHMAD SYAQEER BIN S | PAYMENT/TRANSFER OTHR AHMAD SYAQEER BIN S Transfer | OTHR | PAYMENT/TRANSFER","AHMAD SYAQEER BIN S","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"04/09/2020","01/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"04/09/2020","01/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"04/09/2020","01/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-17.1,"04/09/2020","01/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(389.55,"04/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 03/09/2020 | POS SETTLEMENT","ATAN MOTOR","03/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(217,"04/09/2020","MOHAMMAD ALI BIN LA | PAYMENT/TRANSFER OTHR S$ MOHAMMAD ALI BIN LA via PayNow: FBK863U | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD ALI BIN LA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(395,"04/09/2020","VICKIE LEE HONG MUI | PAYMENT/TRANSFER OTHR S$ VICKIE LEE HONG MUI via PayNow: Fbn5890s | OTHR S$ | PAYMENT/TRANSFER","VICKIE LEE HONG MUI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(185,"04/09/2020","from NORMALA BINTE | OTHR - FBQ9771K FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NORMALA BINTE","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"04/09/2020","NORASHIKIN BINTE RA | PAYMENT/TRANSFER OTHR NORASHIKIN BINTE RA Transfer | OTHR | PAYMENT/TRANSFER","NORASHIKIN BINTE RA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"04/09/2020","TAN SHIYUAN MAUREEN | PAYMENT/TRANSFER OTHR S$ TAN SHIYUAN MAUREEN via PayNow: Sep | OTHR S$ | PAYMENT/TRANSFER","TAN SHIYUAN MAUREEN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(308,"04/09/2020","FBP3420T | CASH DEPOSIT |  | CASH DEPOSIT","FBP3420T","","CASH DEPOSIT"));
        bankRecords.add(new Transaction(-21271.84,"04/09/2020","P2009SF1 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 21 271.84@1 Atan Motoring 11th Sep Payment | CT0019470377 | FUND TRANSFER","P2009SF1","CT0019470377","FUND TRANSFER"));
        bankRecords.add(new Transaction(355,"04/09/2020","TING CHIN KIONG | PAYMENT/TRANSFER OTHR TING CHIN KIONG FBN847B   S8466039F | OTHR | PAYMENT/TRANSFER","TING CHIN KIONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-867.35,"04/09/2020","P200906 | FUND TRANSFER Fund Transfer to account 653320218001 SGD 867.35@1 Atan Motoring Apr-Aug 2020 | CT0019471266 | FUND TRANSFER","P200906","CT0019471266","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"04/09/2020","LIANG WAI KEAN | SI               FBQ7274S IBG GIRO | OTHR | IBG GIRO","LIANG WAI KEAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(350,"04/09/2020","SIVAKUMAR A/L G RAM | SI               FBP7760K IBG GIRO | OTHR | IBG GIRO","SIVAKUMAR A/L G RAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-42.1,"04/09/2020","LTA | LTAFBC2384P IBG GIRO | PADD 051108800008 | IBG GIRO","LTA","PADD 051108800008","IBG GIRO"));
        bankRecords.add(new Transaction(300,"04/09/2020","MUHAMMAD HAIKAL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIKAL BIN via PayNow: Payment for - FBB8315E | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIKAL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(172,"04/09/2020","CHEN TIAM JIAM | PAYMENT/TRANSFER OTHR S$ CHEN TIAM JIAM via PayNow: FOR FBD8296R | OTHR S$ | PAYMENT/TRANSFER","CHEN TIAM JIAM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(306,"05/09/2020","LIM KOK HWA | PAYMENT/TRANSFER OTHR S$ LIM KOK HWA via PayNow: FBR 1035 A Lim Kok Hwa | OTHR S$ | PAYMENT/TRANSFER","LIM KOK HWA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-150.95,"05/09/2020","44076 | xx-8008 M1_DIGITAL BILL PAYMEN SG DEBIT PURCHASE |  | DEBIT PURCHASE","44076","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(123,"05/09/2020","from FBQ4411H NUR'I | OTHR - FBQ4411H FUND TRANSFER |  | FUND TRANSFER","from FBQ4411H NUR'I","","FUND TRANSFER"));
        bankRecords.add(new Transaction(147,"05/09/2020","SIAH SENG CHONG | PAYMENT/TRANSFER LOAN SIAH SENG CHONG FBM7218B Alex | LOAN | PAYMENT/TRANSFER","SIAH SENG CHONG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(130,"05/09/2020","MUHAMMAD FADZLI BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FADZLI BIN via PayNow: FBG1164T Installment | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FADZLI BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(266,"05/09/2020","NUR'ATIKAH BINTE MO | PAYMENT/TRANSFER OTHR NUR'ATIKAH BINTE MO Ins plus Roadtax | OTHR | PAYMENT/TRANSFER","NUR'ATIKAH BINTE MO","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(226,"05/09/2020","from AZAHAR BIN ABD | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from AZAHAR BIN ABD","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(150,"05/09/2020","WONG CHIN KEAT | PAYMENT/TRANSFER OTHR S$ WONG CHIN KEAT via PayNow: FBF7229G | OTHR S$ | PAYMENT/TRANSFER","WONG CHIN KEAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"05/09/2020","MUHAMMAD I'ZZUN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD I'ZZUN BIN via PayNow: FBN7299L | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD I'ZZUN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(273.7,"05/09/2020","MUHD HADI BIN HALI | PAYMENT/TRANSFER OTHR S$ MUHD HADI BIN HALI via PayNow: FBL193P | OTHR S$ | PAYMENT/TRANSFER","MUHD HADI BIN HALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(195,"05/09/2020","SAHAADHEVAN S/O PAT | PAYMENT/TRANSFER OTHR S$ SAHAADHEVAN S/O PAT via PayNow: FBQ4954H | OTHR S$ | PAYMENT/TRANSFER","SAHAADHEVAN S/O PAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"05/09/2020","MOHAMMED SHAFEERUDE | PAYMENT/TRANSFER OTHR S$ MOHAMMED SHAFEERUDE via PayNow: 198903552w | OTHR S$ | PAYMENT/TRANSFER","MOHAMMED SHAFEERUDE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(110,"05/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-26.3,"07/09/2020","44079 | xx-7976 FAIRPRICE XTRA-KALLANG WAS DEBIT PURCHASE |  | DEBIT PURCHASE","44079","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(352,"07/09/2020","VILLAFUERTE ARIEL D | PAYMENT/TRANSFER COLL VILLAFUERTE ARIEL D FBR1665G SEPT2020 | COLL | PAYMENT/TRANSFER","VILLAFUERTE ARIEL D","COLL","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(240,"07/09/2020","ARHAB SADEED | PAYMENT/TRANSFER OTHR S$ ARHAB SADEED via PayNow: FBJ8075E | OTHR S$ | PAYMENT/TRANSFER","ARHAB SADEED","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(322,"07/09/2020","MUHAMMAD AMIRUL HAK | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AMIRUL HAK via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AMIRUL HAK","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(217,"07/09/2020","from FBQ8185J HARUN | OTHR - FBQ8185J FUND TRANSFER |  | FUND TRANSFER","from FBQ8185J HARUN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(484,"07/09/2020","INDRA SUKMA BIN BUH | PAYMENT/TRANSFER IHRP INDRA SUKMA BIN BUH FBP6139L- SEPTEMBER 2020 | IHRP | PAYMENT/TRANSFER","INDRA SUKMA BIN BUH","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3220.9,"07/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 04/09/2020 | POS SETTLEMENT","ATAN MOTOR","04/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(90.85,"07/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 05/09/2020 | POS SETTLEMENT","ATAN MOTOR","05/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-842.09,"07/09/2020","P200909 | GIRO PAYMENT |  | GIRO PAYMENT","P200909","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-1697.5,"07/09/2020","P200908 | GIRO PAYMENT |  | GIRO PAYMENT","P200908","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-757.56,"07/09/2020","P200905 | GIRO PAYMENT |  | GIRO PAYMENT","P200905","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"07/09/2020","P200909 | GIRO CHARGES |  | GIRO CHARGES","P200909","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"07/09/2020","P200908 | GIRO CHARGES |  | GIRO CHARGES","P200908","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"07/09/2020","P200905 | GIRO CHARGES |  | GIRO CHARGES","P200905","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-25,"07/09/2020","04/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","04/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"07/09/2020","04/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","04/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-64,"07/09/2020","04/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","04/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(215,"07/09/2020","from AFK ALAN FROST | TRPT - FBN2826Z FUND TRANSFER |  | FUND TRANSFER","from AFK ALAN FROST","","FUND TRANSFER"));
        bankRecords.add(new Transaction(304,"07/09/2020","LOH CHIANG LONG | PAYMENT/TRANSFER TRPT LOH CHIANG LONG FBQ8258H | TRPT | PAYMENT/TRANSFER","LOH CHIANG LONG","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(340,"07/09/2020","SARAVANAN NADARAJA | PAYMENT/TRANSFER OTHR S$ SARAVANAN NADARAJA via PayNow: Transfer - UEN | OTHR S$ | PAYMENT/TRANSFER","SARAVANAN NADARAJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-356.32,"07/09/2020","P2009SF2 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 356.32@1 Blk: 20714  FBH8320H F/S | CT0019480619 | FUND TRANSFER","P2009SF2","CT0019480619","FUND TRANSFER"));
        bankRecords.add(new Transaction(361,"07/09/2020","MOHD BAKHTIAR BIN U | PAYMENT/TRANSFER OTHR MOHD BAKHTIAR BIN U FBP3055P monthly installment | OTHR | PAYMENT/TRANSFER","MOHD BAKHTIAR BIN U","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(199,"07/09/2020","from CHANG POO TECK | OTHR - PayNow Transfer FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from CHANG POO TECK","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"07/09/2020","NAZRE BIN ABU BAKAR | PAYMENT/TRANSFER OTHR S$ NAZRE BIN ABU BAKAR via PayNow: fbp9156s | OTHR S$ | PAYMENT/TRANSFER","NAZRE BIN ABU BAKAR","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(30,"07/09/2020","MOHAMMED SHAFEERUDE | PAYMENT/TRANSFER OTHR S$ MOHAMMED SHAFEERUDE via PayNow: Fbj8432H | OTHR S$ | PAYMENT/TRANSFER","MOHAMMED SHAFEERUDE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(127,"07/09/2020","NAZRE BIN ABU BAKAR | PAYMENT/TRANSFER OTHR S$ NAZRE BIN ABU BAKAR via PayNow: fbp9156s | OTHR S$ | PAYMENT/TRANSFER","NAZRE BIN ABU BAKAR","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(385.8,"07/09/2020","KOH YEOW HUI | PAYMENT/TRANSFER OTHR S$ KOH YEOW HUI via PayNow: FBC2232U | OTHR S$ | PAYMENT/TRANSFER","KOH YEOW HUI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"07/09/2020","SUBRAMANIAM S/O MAY | PAYMENT/TRANSFER OTHR SUBRAMANIAM S/O MAY Transfer | OTHR | PAYMENT/TRANSFER","SUBRAMANIAM S/O MAY","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(272,"07/09/2020","SELVAM RAMKUMAR | SI               FBL8962J IBG GIRO | OTHR | IBG GIRO","SELVAM RAMKUMAR","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(250,"07/09/2020","BAHARIN BIN OSMAN | SI               FBQ8329L IBG GIRO | OTHR | IBG GIRO","BAHARIN BIN OSMAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(228,"07/09/2020","NORDIN BIN YUSOF | SI               FBP5784E IBG GIRO | OTHR | IBG GIRO","NORDIN BIN YUSOF","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(270,"07/09/2020","MUHAMMAD SUFIAN BIN | SI               FBL6449M IBG GIRO | OTHR | IBG GIRO","MUHAMMAD SUFIAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(242,"07/09/2020","RAJA AFIQAH QISTINA | SI               FBN5666Z IBG GIRO | OTHR | IBG GIRO","RAJA AFIQAH QISTINA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(226,"07/09/2020","MOHAMED HASDI BIN M | SI               FBQ7850D IBG GIRO | OTHR | IBG GIRO","MOHAMED HASDI BIN M","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(186,"07/09/2020","KASMANI BIN KAMIS | SI               FBM1760R IBG GIRO | OTHR | IBG GIRO","KASMANI BIN KAMIS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(200,"07/09/2020","MOHD SAFARUL HAFIF | SI               FBN3623K IBG GIRO | OTHR | IBG GIRO","MOHD SAFARUL HAFIF","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(363,"07/09/2020","ISHAK BIN SUBARI | SI               FBN3663U IBG GIRO | OTHR | IBG GIRO","ISHAK BIN SUBARI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(350,"07/09/2020","MOHD ROSLAN B MARWA | PAYMENT/TRANSFER OTHR MOHD ROSLAN B MARWA Transfer | OTHR | PAYMENT/TRANSFER","MOHD ROSLAN B MARWA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(261,"08/09/2020","ABDULLAH KHALESH KA | PAYMENT/TRANSFER OTHR S$ ABDULLAH KHALESH KA via PayNow: FBP 8163 C | OTHR S$ | PAYMENT/TRANSFER","ABDULLAH KHALESH KA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(255,"08/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(397,"08/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 07/09/2020 | POS SETTLEMENT","ATAN MOTOR","07/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-400,"08/09/2020","P200902 | GIRO PAYMENT |  | GIRO PAYMENT","P200902","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"08/09/2020","P200902 | GIRO CHARGES |  | GIRO CHARGES","P200902","","GIRO CHARGES"));
        bankRecords.add(new Transaction(100,"08/09/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"08/09/2020","MOHAMED AJMEL BIN M | PAYMENT/TRANSFER OTHR MOHAMED AJMEL BIN M fba2060j Ajmel | OTHR | PAYMENT/TRANSFER","MOHAMED AJMEL BIN M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(209,"08/09/2020","NOOR HAFIZAH BTE MO | PAYMENT/TRANSFER OTHR S$ NOOR HAFIZAH BTE MO via PayNow: FT2792H | OTHR S$ | PAYMENT/TRANSFER","NOOR HAFIZAH BTE MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"08/09/2020","from Zak ABDUL RAZA | OTHR - Insurance renewal FUND TRANSFER |  | FUND TRANSFER","from Zak ABDUL RAZA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-13225.19,"08/09/2020","P200911 | FUND TRANSFER Fund Transfer to account 618591200001 SGD 13 225.19@1 Inv:1331087535 | CT0019496334 | FUND TRANSFER","P200911","CT0019496334","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"08/09/2020","from DALAIL BIN ABU | TRPT - nil FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from DALAIL BIN ABU","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(218,"08/09/2020","JOYCE THAM SHAO YUN | SI               FBN8982R IBG GIRO | OTHR | IBG GIRO","JOYCE THAM SHAO YUN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(223,"08/09/2020","ABDUL AZIZ BIN ANSA | SI               FBJ8469C IBG GIRO | OTHR | IBG GIRO","ABDUL AZIZ BIN ANSA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-2117,"08/09/2020","DAIMLER FINANCIAL S | HP156344 IBG GIRO | COLL HP156344 | IBG GIRO","DAIMLER FINANCIAL S","COLL HP156344","IBG GIRO"));
        bankRecords.add(new Transaction(280,"08/09/2020","MUHAMMAD NUR ZARIF | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NUR ZARIF via PayNow: FBF9973C | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NUR ZARIF","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(235,"08/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(203,"09/09/2020","FARHANI SHEREEN BTE | PAYMENT/TRANSFER OTHR FARHANI SHEREEN BTE FBP1309X | OTHR | PAYMENT/TRANSFER","FARHANI SHEREEN BTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(254,"09/09/2020","ISMAIL BIN MOHD JAI | PAYMENT/TRANSFER IHRP ISMAIL BIN MOHD JAI FBP2944E Ismail Mohd Jai | IHRP | PAYMENT/TRANSFER","ISMAIL BIN MOHD JAI","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"09/09/2020","SZE WEI KIAT | PAYMENT/TRANSFER OTHR S$ SZE WEI KIAT via PayNow: FW9186X | OTHR S$ | PAYMENT/TRANSFER","SZE WEI KIAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(158,"09/09/2020","HANNAN HUZAIFI BIN | PAYMENT/TRANSFER OTHR S$ HANNAN HUZAIFI BIN via PayNow: Fbk7253j | OTHR S$ | PAYMENT/TRANSFER","HANNAN HUZAIFI BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(820.8,"09/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 08/09/2020 | POS SETTLEMENT","ATAN MOTOR","08/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"09/09/2020","P200910 | GIRO CHARGES |  | GIRO CHARGES","P200910","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-1960,"09/09/2020","P200910 | GIRO PAYMENT |  | GIRO PAYMENT","P200910","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(308,"09/09/2020","LEE GIOK LEE | PAYMENT/TRANSFER OTHR LEE GIOK LEE insurance for fbn3676h | OTHR | PAYMENT/TRANSFER","LEE GIOK LEE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(185,"09/09/2020","LEE GIOK LEE | PAYMENT/TRANSFER OTHR LEE GIOK LEE instalment for Sep fbn3676h | OTHR | PAYMENT/TRANSFER","LEE GIOK LEE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(245,"09/09/2020","MUHAMMAD YAZID BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD YAZID BIN via PayNow: FBL 8613 S | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD YAZID BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(440,"09/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(388,"09/09/2020","MUHAMMAD ZUHAIR JOH | PAYMENT/TRANSFER OTHR MUHAMMAD ZUHAIR JOH Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD ZUHAIR JOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-33444.86,"09/09/2020","186294 | CHQ186294 CHEQUE |  | CHEQUE","186294","","CHEQUE"));
        bankRecords.add(new Transaction(242,"09/09/2020","MUHAMMAD ALI BIN HU | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ALI BIN HU via PayNow: FBN3333Y | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ALI BIN HU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(226,"10/09/2020","KHAIRIL SHAZWAN B S | PAYMENT/TRANSFER OTHR S$ KHAIRIL SHAZWAN B S via PayNow: FBP6263K | OTHR S$ | PAYMENT/TRANSFER","KHAIRIL SHAZWAN B S","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(269,"10/09/2020","ARUNKUMAR S/O ELANG | PAYMENT/TRANSFER OTHR S$ ARUNKUMAR S/O ELANG via PayNow: FBQ9294X | OTHR S$ | PAYMENT/TRANSFER","ARUNKUMAR S/O ELANG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(650.5,"10/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 09/09/2020 | POS SETTLEMENT","ATAN MOTOR","09/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-25,"10/09/2020","07/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","07/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"10/09/2020","07/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","07/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-64,"10/09/2020","07/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","07/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"10/09/2020","07/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","07/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(122,"10/09/2020","NUR RISHDA BINTE MO | PAYMENT/TRANSFER OTHR S$ NUR RISHDA BINTE MO via PayNow: FBG5928E | OTHR S$ | PAYMENT/TRANSFER","NUR RISHDA BINTE MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"10/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-16.05,"10/09/2020","P200912 | FUND TRANSFER Fund Transfer to account 618591200001 SGD 16.05@1 SMCTA JUL 2020 | CT0019516295 | FUND TRANSFER","P200912","CT0019516295","FUND TRANSFER"));
        bankRecords.add(new Transaction(700,"10/09/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN FBP1371P | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(2.4,"10/09/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN fbp1371p | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(195,"10/09/2020","MOHAMMAD KHAIRY SHU | PAYMENT/TRANSFER OTHR MOHAMMAD KHAIRY SHU FBP6099T | OTHR | PAYMENT/TRANSFER","MOHAMMAD KHAIRY SHU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(195,"10/09/2020","MOHAMMAD KHAIRUL AZ | PAYMENT/TRANSFER OTHR S$ MOHAMMAD KHAIRUL AZ via PayNow: FBP6279R | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD KHAIRUL AZ","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"10/09/2020","TAN YEE SENG | PAYMENT/TRANSFER OTHR TAN YEE SENG FBN 2306 H | OTHR | PAYMENT/TRANSFER","TAN YEE SENG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(184,"10/09/2020","V RAJENDRAN | PAYMENT/TRANSFER OTHR S$ V RAJENDRAN via PayNow: FBF9210X | OTHR S$ | PAYMENT/TRANSFER","V RAJENDRAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(322,"10/09/2020","KAU YENG CHING | SI               FBP1031X IBG GIRO | OTHR | IBG GIRO","KAU YENG CHING","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(164,"10/09/2020","MUHAMMAD ISKANDAR B | SI                FW5342M IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ISKANDAR B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(90,"10/09/2020","MUHAMMAD FARHAN BIN | SI               FBB3372Y IBG GIRO | OTHR | IBG GIRO","MUHAMMAD FARHAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(214,"10/09/2020","MOHAMMAD RAMZAN | SI               FBP7651S IBG GIRO | OTHR | IBG GIRO","MOHAMMAD RAMZAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(296,"10/09/2020","ABDUL KHALIQ MARICA | SI               FBM3473G IBG GIRO | OTHR | IBG GIRO","ABDUL KHALIQ MARICA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(270,"10/09/2020","YELEMALAI S/O SUBRA | SI               FBM6729A IBG GIRO | OTHR | IBG GIRO","YELEMALAI S/O SUBRA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-1350,"10/09/2020","HONG LEONG FINANCE | HLF-030 DUE 20200910 IBG GIRO | IHRP 38316717005680 | IBG GIRO","HONG LEONG FINANCE","IHRP 38316717005680","IBG GIRO"));
        bankRecords.add(new Transaction(268,"10/09/2020","ROSLIN HAZLINDA BIN | PAYMENT/TRANSFER OTHR ROSLIN HAZLINDA BIN Transfer | OTHR | PAYMENT/TRANSFER","ROSLIN HAZLINDA BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(239,"10/09/2020","NURAISHAH BINTE HAS | PAYMENT/TRANSFER TRPT NURAISHAH BINTE HAS FBP0184U Md Nafee Sukiman | TRPT | PAYMENT/TRANSFER","NURAISHAH BINTE HAS","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(256,"10/09/2020","NURAISHAH BINTE HAS | PAYMENT/TRANSFER TRPT NURAISHAH BINTE HAS FBM5172L Sukiman | TRPT | PAYMENT/TRANSFER","NURAISHAH BINTE HAS","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(275,"10/09/2020","HAFIZAH BINTE JUMA' | PAYMENT/TRANSFER OTHR S$ HAFIZAH BINTE JUMA' via PayNow: FBK5415Y | OTHR S$ | PAYMENT/TRANSFER","HAFIZAH BINTE JUMA'","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"10/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(300,"10/09/2020","SABRINA BINTE SALIM | PAYMENT/TRANSFER OTHR SABRINA BINTE SALIM fbl679e | OTHR | PAYMENT/TRANSFER","SABRINA BINTE SALIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-3.75,"10/09/2020","186292 | CHQ186292 CHEQUE |  | CHEQUE","186292","","CHEQUE"));
        bankRecords.add(new Transaction(258,"11/09/2020","MUHAMMAD NUR HAKEEM | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NUR HAKEEM via PayNow: FBQ7609K | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NUR HAKEEM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(220,"11/09/2020","NIK HASLINA BINTE B | PAYMENT/TRANSFER OTHR NIK HASLINA BINTE B FBP1967X  SEP2020 | OTHR | PAYMENT/TRANSFER","NIK HASLINA BINTE B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3659.55,"11/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 10/09/2020 | POS SETTLEMENT","ATAN MOTOR","10/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-25,"11/09/2020","08/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","08/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"11/09/2020","08/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","08/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"11/09/2020","08/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","08/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(292,"11/09/2020","from Payment MUHAMA | OTHR - FBR 659 G FUND TRANSFER |  | FUND TRANSFER","from Payment MUHAMA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(268,"11/09/2020","MUHAMMAD ZULKHAIRI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZULKHAIRI via PayNow: FBG6838A | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZULKHAIRI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"11/09/2020","THANASELAN A/L MURU | PAYMENT/TRANSFER OTHR S$ THANASELAN A/L MURU via PayNow: FBQ5848A | OTHR S$ | PAYMENT/TRANSFER","THANASELAN A/L MURU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"11/09/2020","SYAZMAN LUTFI BIN M | PAYMENT/TRANSFER OTHR SYAZMAN LUTFI BIN M FBM 8575M | OTHR | PAYMENT/TRANSFER","SYAZMAN LUTFI BIN M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"11/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(292,"11/09/2020","KAVIARASAN A/L SELL | SI               FBN5471T IBG GIRO | OTHR | IBG GIRO","KAVIARASAN A/L SELL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(307,"11/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(198,"11/09/2020","from Moto shop ROSL | LOAR - Fbn3212k FUND TRANSFER |  | FUND TRANSFER","from Moto shop ROSL","","FUND TRANSFER"));
        bankRecords.add(new Transaction(2660,"11/09/2020","287685 | OCBC CHQ287685 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","287685","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(120,"11/09/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-994.55,"11/09/2020","186371 | CHQ186371 CHEQUE |  | CHEQUE","186371","","CHEQUE"));
        bankRecords.add(new Transaction(-1428.7,"11/09/2020","186293 | CHQ186293 CHEQUE |  | CHEQUE","186293","","CHEQUE"));
        bankRecords.add(new Transaction(-11901.33,"11/09/2020","186196 | CHQ186196 CHEQUE |  | CHEQUE","186196","","CHEQUE"));
        bankRecords.add(new Transaction(239,"12/09/2020","MUHAMMAD JAMIL BIN | PAYMENT/TRANSFER IHRP MUHAMMAD JAMIL BIN FBR 2008 y | IHRP | PAYMENT/TRANSFER","MUHAMMAD JAMIL BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(206,"12/09/2020","MUHAMMAD SHUKOR BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SHUKOR BIN FBP9788T For month of Oct | OTHR | PAYMENT/TRANSFER","MUHAMMAD SHUKOR BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(307,"12/09/2020","LIM FU JIANG | PAYMENT/TRANSFER OTHR S$ LIM FU JIANG via PayNow: FBP8110E | OTHR S$ | PAYMENT/TRANSFER","LIM FU JIANG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"12/09/2020","09/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","09/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(241,"12/09/2020","NUR AZAM BIN AB RAH | PAYMENT/TRANSFER OTHR NUR AZAM BIN AB RAH FBN8042D | OTHR | PAYMENT/TRANSFER","NUR AZAM BIN AB RAH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(330,"12/09/2020","RIDZAL BIN BUANG | PAYMENT/TRANSFER OTHR RIDZAL BIN BUANG FBP6334P | OTHR | PAYMENT/TRANSFER","RIDZAL BIN BUANG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(363,"12/09/2020","AZMAN BIN YUSOF | PAYMENT/TRANSFER OTHR S$ AZMAN BIN YUSOF via PayNow: FBQ373K | OTHR S$ | PAYMENT/TRANSFER","AZMAN BIN YUSOF","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(218,"12/09/2020","from MOHAMED HISHAM | OTHR - FBQ4888R FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MOHAMED HISHAM","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(-904.15,"12/09/2020","P200907 | FUND TRANSFER Fund Transfer to account 601265671001 SGD 904.15@1 Atan Motoring Supply Pte Ltd - Aug 2020 | CT0019530884 | FUND TRANSFER","P200907","CT0019530884","FUND TRANSFER"));
        bankRecords.add(new Transaction(314,"12/09/2020","MUHAMMAD SAHRIN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SAHRIN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD SAHRIN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(168,"12/09/2020","SUPARDE BIN SATJA | PAYMENT/TRANSFER OTHR SUPARDE BIN SATJA Transfer | OTHR | PAYMENT/TRANSFER","SUPARDE BIN SATJA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(167,"12/09/2020","ASTIKA BIN ARHAM | PAYMENT/TRANSFER IHRP ASTIKA BIN ARHAM FBF9880L | IHRP | PAYMENT/TRANSFER","ASTIKA BIN ARHAM","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(479,"12/09/2020","from Dzulhilmy MOHA | OTHR - FBJ927A FUND TRANSFER |  | FUND TRANSFER","from Dzulhilmy MOHA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(174,"12/09/2020","MUHAMMAD SHAFIQ BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SHAFIQ BIN via PayNow: FBP782T | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SHAFIQ BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(226,"12/09/2020","RADIWATI BTE MOHAME | PAYMENT/TRANSFER OTHR RADIWATI BTE MOHAME FBR1534B | OTHR | PAYMENT/TRANSFER","RADIWATI BTE MOHAME","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(265,"12/09/2020","MUHAMMAD AMEEN BADE | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AMEEN BADE via PayNow: FBJ3942K | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AMEEN BADE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(258,"12/09/2020","MOHAMED SHAHRIL BIN | PAYMENT/TRANSFER OTHR MOHAMED SHAHRIL BIN shahril fbq671a yamaha mt15 | OTHR | PAYMENT/TRANSFER","MOHAMED SHAHRIL BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(193,"14/09/2020","FOREST CHILD CADRE | PAYMENT/TRANSFER ALLW FOREST CHILD CADRE EBGPP00912760247 | ALLW | PAYMENT/TRANSFER","FOREST CHILD CADRE","ALLW","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"14/09/2020","10/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","10/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-82,"14/09/2020","10/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","10/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(246,"14/09/2020","from FBK5286C SHARE | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBK5286C SHARE","","FUND TRANSFER"));
        bankRecords.add(new Transaction(202,"14/09/2020","MUHAMMAD NUR HAZWAN | PAYMENT/TRANSFER OTHR MUHAMMAD NUR HAZWAN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD NUR HAZWAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(9950,"14/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(10000,"14/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(5850,"14/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(360,"14/09/2020","RAZALI BIN JOHARI | PAYMENT/TRANSFER OTHR RAZALI BIN JOHARI Transfer | OTHR | PAYMENT/TRANSFER","RAZALI BIN JOHARI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"14/09/2020","from FBD7182L MUHAM | OTHR - FBP1782L FUND TRANSFER |  | FUND TRANSFER","from FBD7182L MUHAM","","FUND TRANSFER"));
        bankRecords.add(new Transaction(202,"14/09/2020","NURHIDAYAH BTE MOLI | PAYMENT/TRANSFER OTHR S$ NURHIDAYAH BTE MOLI via PayNow: FBL6182G | OTHR S$ | PAYMENT/TRANSFER","NURHIDAYAH BTE MOLI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(267,"14/09/2020","MUHAMMAD AFIQ BIN A | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AFIQ BIN A via PayNow: FBP1376B | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AFIQ BIN A","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(216,"14/09/2020","MUHAMMAD ANDIKA BIN | PAYMENT/TRANSFER OTHR MUHAMMAD ANDIKA BIN FBP4314K | OTHR | PAYMENT/TRANSFER","MUHAMMAD ANDIKA BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"14/09/2020","MUHAMMAD ZUHAIDI BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZUHAIDI BI via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZUHAIDI BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-126.09,"14/09/2020","44085 | xx-8008 ESSO-CHEERS BY FP      SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44085","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(707.05,"14/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 11/09/2020 | POS SETTLEMENT","ATAN MOTOR","11/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(2216,"14/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 12/09/2020 | POS SETTLEMENT","ATAN MOTOR","12/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"14/09/2020","P200912 | GIRO CHARGES |  | GIRO CHARGES","P200912","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-175,"14/09/2020","P200912 | GIRO PAYMENT |  | GIRO PAYMENT","P200912","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(12.6,"14/09/2020","from # TAN HWA MENG | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from # TAN HWA MENG","","FUND TRANSFER"));
        bankRecords.add(new Transaction(359,"14/09/2020","411932 | DBS BANK CHQ411932 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","411932","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-23055.36,"14/09/2020","P2009SF3 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 23 055.36@1 Atan Motoring 21st Sep Payment | CT0019540879 | FUND TRANSFER","P2009SF3","CT0019540879","FUND TRANSFER"));
        bankRecords.add(new Transaction(193.29,"14/09/2020","KOH YEOW HUI | SI               FBC2232U IBG GIRO | OTHR | IBG GIRO","KOH YEOW HUI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(301,"14/09/2020","YEOH AH FOCK | SI               FBQ8386X IBG GIRO | OTHR | IBG GIRO","YEOH AH FOCK","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(202,"14/09/2020","WAHAB BIN MOHD ISA | SI               FBP2238K IBG GIRO | OTHR | IBG GIRO","WAHAB BIN MOHD ISA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(307,"14/09/2020","RITA ZAHARA BTE KAM | SI               FBN5441E IBG GIRO | OTHR | IBG GIRO","RITA ZAHARA BTE KAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(363,"14/09/2020","TAN CHUN CHANG | SI               FBM8030T IBG GIRO | OTHR | IBG GIRO","TAN CHUN CHANG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(201,"14/09/2020","SHAHRUL ANDEKA BIN | SI               FBM1787P IBG GIRO | OTHR | IBG GIRO","SHAHRUL ANDEKA BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(236,"14/09/2020","WONG CHEE SONG | SI               FBN7766E IBG GIRO | OTHR | IBG GIRO","WONG CHEE SONG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(200,"14/09/2020","AMILDA DEWI BINTE A | SI               FBN9391P IBG GIRO | OTHR | IBG GIRO","AMILDA DEWI BINTE A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(167,"14/09/2020","MUHAMMAD ADZLAN BIN | SI               FBP5843T IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ADZLAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"14/09/2020","MOHAMMAD FAREHIN BI | SI               FBN2209E IBG GIRO | OTHR | IBG GIRO","MOHAMMAD FAREHIN BI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(241,"14/09/2020","MUHAMMAD SHAH INDRA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SHAH INDRA via PayNow: FBF4280B | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SHAH INDRA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(258,"15/09/2020","NUR MUHAMMAD FIRDAU | PAYMENT/TRANSFER OTHR S$ NUR MUHAMMAD FIRDAU via PayNow: Transfer - UEN | OTHR S$ | PAYMENT/TRANSFER","NUR MUHAMMAD FIRDAU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(234,"15/09/2020","MOHAMMAD RIDZUAN BI | PAYMENT/TRANSFER OTHR S$ MOHAMMAD RIDZUAN BI via PayNow: FBM8055Y | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD RIDZUAN BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"15/09/2020","SAIFUL AMIRIN BIN J | PAYMENT/TRANSFER OTHR SAIFUL AMIRIN BIN J Transfer | OTHR | PAYMENT/TRANSFER","SAIFUL AMIRIN BIN J","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260.65,"15/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 14/09/2020 | POS SETTLEMENT","ATAN MOTOR","14/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"15/09/2020","P200903 | GIRO CHARGES |  | GIRO CHARGES","P200903","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-400,"15/09/2020","P200903 | GIRO PAYMENT |  | GIRO PAYMENT","P200903","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(325,"15/09/2020","from ZAIM ZA'IM AL- | TRPT - FBM3243D FUND TRANSFER |  | FUND TRANSFER","from ZAIM ZA'IM AL-","","FUND TRANSFER"));
        bankRecords.add(new Transaction(180,"15/09/2020","HAZLIATI BINTE ABDU | PAYMENT/TRANSFER OTHR HAZLIATI BINTE ABDU FBK1608J | OTHR | PAYMENT/TRANSFER","HAZLIATI BINTE ABDU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"15/09/2020","SRI NUR ASMA-UL-HUS | PAYMENT/TRANSFER OTHR S$ SRI NUR ASMA-UL-HUS via PayNow: Fbq2362d | OTHR S$ | PAYMENT/TRANSFER","SRI NUR ASMA-UL-HUS","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(15,"15/09/2020","PADGETT DAVID S # | PAYMENT/TRANSFER OTHR S$ PADGETT DAVID S # via PayNow: Padgett | OTHR S$ | PAYMENT/TRANSFER","PADGETT DAVID S #","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-10064.44,"15/09/2020","P200913 | FUND TRANSFER Fund Transfer to account 507076347001 SGD 10 064.44@1 Atan Motoring Supply Pte Ltd: Apr - Jul 2020 | CT0019549984 | FUND TRANSFER","P200913","CT0019549984","FUND TRANSFER"));
        bankRecords.add(new Transaction(356,"15/09/2020","AMSYAR DINIE BIN AB | PAYMENT/TRANSFER OTHR S$ AMSYAR DINIE BIN AB via PayNow: FR2184Z | OTHR S$ | PAYMENT/TRANSFER","AMSYAR DINIE BIN AB","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(606,"15/09/2020","from ZA'IM AL-MUQSI | TRPT - Transport FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from ZA'IM AL-MUQSI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(200,"15/09/2020","SALEH BIN RIK | SI               FBN7941R IBG GIRO | OTHR | IBG GIRO","SALEH BIN RIK","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(310,"15/09/2020","ROBIAH BINTE HAMIDO | SI                FBK803T IBG GIRO | OTHR | IBG GIRO","ROBIAH BINTE HAMIDO","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(192,"15/09/2020","ALDREN MICHAEL KOH | SI               FBM4788S IBG GIRO | OTHR | IBG GIRO","ALDREN MICHAEL KOH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(242,"15/09/2020","JOACHIM DAVID SATIS | SI               FBL6204C IBG GIRO | OTHR | IBG GIRO","JOACHIM DAVID SATIS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(291,"15/09/2020","MUHAMMAD NABIL BIN | SI               FBK6972Z IBG GIRO | OTHR | IBG GIRO","MUHAMMAD NABIL BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(241,"15/09/2020","RAMARAU A/L SIMMTHE | SI               FBP6774C IBG GIRO | OTHR | IBG GIRO","RAMARAU A/L SIMMTHE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(212,"15/09/2020","KHAJAA SHAS S/O YAB | SI               FBH5802D IBG GIRO | OTHR | IBG GIRO","KHAJAA SHAS S/O YAB","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(214,"15/09/2020","MUHAMMAD RAZEEF BIN | SI               FBN6035M IBG GIRO | OTHR | IBG GIRO","MUHAMMAD RAZEEF BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(230,"15/09/2020","SARRAVANAN PATMANAT | PAYMENT/TRANSFER OTHR S$ SARRAVANAN PATMANAT via PayNow: FBQ4588G | OTHR S$ | PAYMENT/TRANSFER","SARRAVANAN PATMANAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(891.8,"15/09/2020","MUHAMMAD FARHAN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FARHAN BIN via PayNow: Fbb3372y | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FARHAN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"16/09/2020","MOHAMED AJMEL BIN M | PAYMENT/TRANSFER OTHR MOHAMED AJMEL BIN M fba 2060j ajmel | OTHR | PAYMENT/TRANSFER","MOHAMED AJMEL BIN M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(244,"16/09/2020","THANIUS BIN MUSNI | PAYMENT/TRANSFER OTHR THANIUS BIN MUSNI INSTALLMENT NO 41 FOR BIKE FBL8716 | OTHR | PAYMENT/TRANSFER","THANIUS BIN MUSNI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1052.85,"16/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 15/09/2020 | POS SETTLEMENT","ATAN MOTOR","15/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-2796,"16/09/2020","P200914 | GIRO PAYMENT |  | GIRO PAYMENT","P200914","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"16/09/2020","P200914 | GIRO CHARGES |  | GIRO CHARGES","P200914","","GIRO CHARGES"));
        bankRecords.add(new Transaction(258,"16/09/2020","G200916 | GIRO COLLECTION |  | GIRO COLLECTION","G200916","","GIRO COLLECTION"));
        bankRecords.add(new Transaction(-0.2,"16/09/2020","G200916 | GIRO CHARGES |  | GIRO CHARGES","G200916","","GIRO CHARGES"));
        bankRecords.add(new Transaction(227,"16/09/2020","from FBL4428L HASLI | TRPT - Transport FUND TRANSFER |  | FUND TRANSFER","from FBL4428L HASLI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(240,"16/09/2020","from FBQ4766J SHAFI | OTHR - FBQ4766J FUND TRANSFER |  | FUND TRANSFER","from FBQ4766J SHAFI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(227,"16/09/2020","YAP LIP MAN | PAYMENT/TRANSFER OTHR S$ YAP LIP MAN via PayNow: fbq3495y | OTHR S$ | PAYMENT/TRANSFER","YAP LIP MAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(183,"16/09/2020","KAMSANI BIN MD AMIN | PAYMENT/TRANSFER OTHR S$ KAMSANI BIN MD AMIN via PayNow: FBJ3745M | OTHR S$ | PAYMENT/TRANSFER","KAMSANI BIN MD AMIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(444,"16/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(300,"16/09/2020","POOBALAN LOGANATHAN | PAYMENT/TRANSFER IHRP POOBALAN LOGANATHAN Transfer | IHRP | PAYMENT/TRANSFER","POOBALAN LOGANATHAN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(224,"16/09/2020","MOHD HAZIZI BIN KAS | PAYMENT/TRANSFER OTHR MOHD HAZIZI BIN KAS Transfer | OTHR | PAYMENT/TRANSFER","MOHD HAZIZI BIN KAS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1052,"16/09/2020","NUR RISHDA BINTE MO | PAYMENT/TRANSFER OTHR S$ NUR RISHDA BINTE MO via PayNow: FBG5928E insurance | OTHR S$ | PAYMENT/TRANSFER","NUR RISHDA BINTE MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(296,"16/09/2020","from SEE YAO JIE RO | OTHR - FBJ9013C Insurance FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from SEE YAO JIE RO","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(295,"16/09/2020","MOOVAANAN S/O KUNAS | SI               FBN3339G IBG GIRO | OTHR | IBG GIRO","MOOVAANAN S/O KUNAS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(500,"16/09/2020","MUHAMMAD FAIZ BIN H | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FAIZ BIN H via PayNow: CB400 Spec 2 fw7324c | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FAIZ BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(25791.88,"16/09/2020","B01 23838 | TRANSFER Fund Transfer from SINGAPURA FINANCE LTD SGD 25 791.88 B01 23838 | CT0019562633 | TRANSFER","B01 23838","CT0019562633","TRANSFER"));
        bankRecords.add(new Transaction(233,"16/09/2020","TEO FA YU ALVIN | PAYMENT/TRANSFER OTHR TEO FA YU ALVIN fbk974h | OTHR | PAYMENT/TRANSFER","TEO FA YU ALVIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-4500,"16/09/2020","186298 | CHQ186298 CHEQUE |  | CHEQUE","186298","","CHEQUE"));
        bankRecords.add(new Transaction(214,"17/09/2020","NUR'ATIKAH BINTE MO | PAYMENT/TRANSFER OTHR NUR'ATIKAH BINTE MO Transfer | OTHR | PAYMENT/TRANSFER","NUR'ATIKAH BINTE MO","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(403.95,"17/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 16/09/2020 | POS SETTLEMENT","ATAN MOTOR","16/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-88,"17/09/2020","44088 | xx-7976 COLD STORAGE KALLANG L SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44088","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"17/09/2020","14/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","14/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"17/09/2020","14/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","14/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-6291,"17/09/2020","CPF | BIZ GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(-1650,"17/09/2020","CPF | BFWL GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(400,"17/09/2020"," | xx-0326 OCBC-PG WATERWAY BR      S CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(350,"17/09/2020","SALAHEDDIN BIN SHAI | PAYMENT/TRANSFER IHRP SALAHEDDIN BIN SHAI FBQ4887T | IHRP | PAYMENT/TRANSFER","SALAHEDDIN BIN SHAI","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1000,"17/09/2020","MUHAMMAD SUFIAN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SUFIAN BIN Sufian 97762644 xmax 300 deposit | OTHR | PAYMENT/TRANSFER","MUHAMMAD SUFIAN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(278,"17/09/2020","HUTCHEON ANDREW JOH | PAYMENT/TRANSFER OTHR HUTCHEON ANDREW JOH FBL2071U | OTHR | PAYMENT/TRANSFER","HUTCHEON ANDREW JOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230.4,"17/09/2020","NURUL KHAIRUNNISA B | PAYMENT/TRANSFER OTHR NURUL KHAIRUNNISA B FBP994Z | OTHR | PAYMENT/TRANSFER","NURUL KHAIRUNNISA B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(170,"17/09/2020","ABDUL RAHIM BIN YUS | SI               FBH1648J IBG GIRO | OTHR | IBG GIRO","ABDUL RAHIM BIN YUS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(264,"17/09/2020","LIM ZONG WEI | SI               FBL6349T IBG GIRO | OTHR | IBG GIRO","LIM ZONG WEI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(800,"17/09/2020","KHAJAA SHAS S/O YAB | PAYMENT/TRANSFER OTHR S$ KHAJAA SHAS S/O YAB via PayNow: FBH5802D | OTHR S$ | PAYMENT/TRANSFER","KHAJAA SHAS S/O YAB","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-507.5,"17/09/2020","186297 | CHQ186297 CHEQUE |  | CHEQUE","186297","","CHEQUE"));
        bankRecords.add(new Transaction(-62.95,"18/09/2020","44089 | xx-8008 M1_DIGITAL BILL PAYMEN SG DEBIT PURCHASE |  | DEBIT PURCHASE","44089","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-64,"18/09/2020","15/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","15/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(789.1,"18/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 17/09/2020 | POS SETTLEMENT","ATAN MOTOR","17/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(500,"18/09/2020","OLIVER BRUCE STEWAR | PAYMENT/TRANSFER OTHR OLIVER BRUCE STEWAR FBK6406S | OTHR | PAYMENT/TRANSFER","OLIVER BRUCE STEWAR","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(169.25,"18/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(144,"18/09/2020","MUHAMMAD MUZZAMMIL | PAYMENT/TRANSFER OTHR S$ MUHAMMAD MUZZAMMIL via PayNow: FBG8423A | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD MUZZAMMIL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(319.55,"18/09/2020","CHONG KOK THYE | PAYMENT/TRANSFER OTHR S$ CHONG KOK THYE via PayNow: FBQ7228B | OTHR S$ | PAYMENT/TRANSFER","CHONG KOK THYE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"18/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(285,"18/09/2020","CHAWLA AMIT | SI               FBM1701L IBG GIRO | OTHR | IBG GIRO","CHAWLA AMIT","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(221,"18/09/2020","LIM BOON LEONG | SI               FBN9177S IBG GIRO | OTHR | IBG GIRO","LIM BOON LEONG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(189,"18/09/2020","MUHAMMAD FARHAN BIN | SI                FU3829A IBG GIRO | OTHR | IBG GIRO","MUHAMMAD FARHAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(200,"18/09/2020","MOHAMED ASIQ ALI S/ | PAYMENT/TRANSFER OTHR S$ MOHAMED ASIQ ALI S/ via PayNow: FBG650M | OTHR S$ | PAYMENT/TRANSFER","MOHAMED ASIQ ALI S/","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(400,"18/09/2020","MUHAMMAD KHAIRUL AN | PAYMENT/TRANSFER IHRP MUHAMMAD KHAIRUL AN FBK6900G | IHRP | PAYMENT/TRANSFER","MUHAMMAD KHAIRUL AN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(0.43,"19/09/2020"," | CASH REBATE CASH REBATE |  | CASH REBATE","","","CASH REBATE"));
        bankRecords.add(new Transaction(0.07,"19/09/2020"," | CASH REBATE CASH REBATE |  | CASH REBATE","","","CASH REBATE"));
        bankRecords.add(new Transaction(425,"19/09/2020","SAZALI BIN KASSIM | PAYMENT/TRANSFER IHRP SAZALI BIN KASSIM FBF 4154H | IHRP | PAYMENT/TRANSFER","SAZALI BIN KASSIM","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-57,"21/09/2020","17/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"21/09/2020","17/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(264,"21/09/2020","MUHAMMAD IZZAN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD IZZAN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD IZZAN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(9950,"21/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(12750,"21/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(120,"21/09/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(272,"21/09/2020","MUHAMMAD RAZMI BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAZMI BIN FBK6296S | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAZMI BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(337,"21/09/2020","G200951 | GIRO COLLECTION |  | GIRO COLLECTION","G200951","","GIRO COLLECTION"));
        bankRecords.add(new Transaction(-0.2,"21/09/2020","G200951 | GIRO CHARGES |  | GIRO CHARGES","G200951","","GIRO CHARGES"));
        bankRecords.add(new Transaction(234.35,"21/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 18/09/2020 | POS SETTLEMENT","ATAN MOTOR","18/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(5471.85,"21/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 19/09/2020 | POS SETTLEMENT","ATAN MOTOR","19/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-64,"21/09/2020","18/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","18/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(231,"21/09/2020","ZULKIFLI BIN MOHAME | PAYMENT/TRANSFER LOAN ZULKIFLI BIN MOHAME FBL2565L | LOAN | PAYMENT/TRANSFER","ZULKIFLI BIN MOHAME","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"21/09/2020","MOHAMED YUSSOFF BIN | PAYMENT/TRANSFER TRPT MOHAMED YUSSOFF BIN FBL1650E | TRPT | PAYMENT/TRANSFER","MOHAMED YUSSOFF BIN","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(234,"21/09/2020","MOHD SHAMSUDIN B MD | PAYMENT/TRANSFER OTHR MOHD SHAMSUDIN B MD FBL6392S | OTHR | PAYMENT/TRANSFER","MOHD SHAMSUDIN B MD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(800,"21/09/2020","FBQ203T | FAST TRANSFER OTHR S$ MAVERICK CA FBQ203T via PayNow: FBQ203T | OTHR S$ MAVERICK CA | FAST TRANSFER","FBQ203T","OTHR S$ MAVERICK CA","FAST TRANSFER"));
        bankRecords.add(new Transaction(0.47,"21/09/2020","from # TAN HWA MENG | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from # TAN HWA MENG","","FUND TRANSFER"));
        bankRecords.add(new Transaction(242,"21/09/2020","JESSICA JESSIE D/O | PAYMENT/TRANSFER OTHR S$ JESSICA JESSIE D/O via PayNow: FBP 9042L -2nd payment | OTHR S$ | PAYMENT/TRANSFER","JESSICA JESSIE D/O","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-1693.12,"21/09/2020","P2009SF5 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 1 693.12@1 Blk: 21380  FBP4146P F/S | CT0019593516 | FUND TRANSFER","P2009SF5","CT0019593516","FUND TRANSFER"));
        bankRecords.add(new Transaction(-3147.48,"21/09/2020","P2009SF4 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 3 147.48@1 Blk: 21955  FBQ203T F/S | CT0019593549 | FUND TRANSFER","P2009SF4","CT0019593549","FUND TRANSFER"));
        bankRecords.add(new Transaction(512,"21/09/2020","NG KIM LOON | SI               FBQ5110U IBG GIRO | OTHR | IBG GIRO","NG KIM LOON","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(267,"21/09/2020","MUHD NUUR SHIDDIQ B | SI                FBR219T IBG GIRO | OTHR | IBG GIRO","MUHD NUUR SHIDDIQ B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(185,"21/09/2020","TNG BOON CHAI | SI               FBM0392B IBG GIRO | OTHR | IBG GIRO","TNG BOON CHAI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(230,"21/09/2020","RAM PRAKASH | SI               FBN8194Z IBG GIRO | OTHR | IBG GIRO","RAM PRAKASH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(236,"21/09/2020","MUHAMMAD AIZAT BIN | SI               FBM4963C IBG GIRO | OTHR | IBG GIRO","MUHAMMAD AIZAT BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-263.92,"21/09/2020","NETS (S) Pte Ltd | F110001291 IBG GIRO | PADD EA4354 | IBG GIRO","NETS (S) Pte Ltd","PADD EA4354","IBG GIRO"));
        bankRecords.add(new Transaction(151,"21/09/2020","from DEANNA ERVINA | OTHR - FBG5913Y FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from DEANNA ERVINA","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(-170,"21/09/2020","186370 | CHQ186370 CHEQUE |  | CHEQUE","186370","","CHEQUE"));
        bankRecords.add(new Transaction(-193.29,"21/09/2020","186299 | CHQ186299 CHEQUE |  | CHEQUE","186299","","CHEQUE"));
        bankRecords.add(new Transaction(354,"22/09/2020","MOHAMED HASHIM BIN | PAYMENT/TRANSFER IHRP MOHAMED HASHIM BIN Irfan - FBM7628B | IHRP | PAYMENT/TRANSFER","MOHAMED HASHIM BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(189,"22/09/2020","SAIFUL BAHRI BIN MD | PAYMENT/TRANSFER IHRP SAIFUL BAHRI BIN MD FBQ8326U | IHRP | PAYMENT/TRANSFER","SAIFUL BAHRI BIN MD","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(310.95,"22/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 21/09/2020 | POS SETTLEMENT","ATAN MOTOR","21/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"22/09/2020","P200904 | GIRO CHARGES |  | GIRO CHARGES","P200904","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-400,"22/09/2020","P200904 | GIRO PAYMENT |  | GIRO PAYMENT","P200904","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(283,"22/09/2020","MOHAMED RAFEE BIN A | PAYMENT/TRANSFER OTHR MOHAMED RAFEE BIN A Transfer | OTHR | PAYMENT/TRANSFER","MOHAMED RAFEE BIN A","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(207,"22/09/2020","FUN GUO HAO | PAYMENT/TRANSFER OTHR FUN GUO HAO FBJ8167Z | OTHR | PAYMENT/TRANSFER","FUN GUO HAO","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(227,"22/09/2020","ALMALIK FAIZAL BIN | PAYMENT/TRANSFER OTHR S$ ALMALIK FAIZAL BIN via PayNow: FU156C | OTHR S$ | PAYMENT/TRANSFER","ALMALIK FAIZAL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(237,"22/09/2020","MUHAMMAD NAZRUL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NAZRUL BIN via PayNow: FBM7871P MUHAMMAD NAZR | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NAZRUL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(335,"22/09/2020","SAVARIMUTHU AROCKIA | SI               FBN6893C IBG GIRO | OTHR | IBG GIRO","SAVARIMUTHU AROCKIA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(248,"22/09/2020","MOHD NOR HASSAN B A | SI               FBN9719D IBG GIRO | OTHR | IBG GIRO","MOHD NOR HASSAN B A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(240,"22/09/2020","NOOR AISYAH BINTE K | SI               FBM3499H IBG GIRO | OTHR | IBG GIRO","NOOR AISYAH BINTE K","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(247,"22/09/2020","SYED THAHA AHMAD B | SI               FBM4180U IBG GIRO | OTHR | IBG GIRO","SYED THAHA AHMAD B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(162,"22/09/2020","ABDUL HAFIQ BIN ABD | SI               FBP3652R IBG GIRO | OTHR | IBG GIRO","ABDUL HAFIQ BIN ABD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(247,"22/09/2020","MUHAMMAD NUR IMAN B | PAYMENT/TRANSFER OTHR MUHAMMAD NUR IMAN B FBP8737Y - Sep20 | OTHR | PAYMENT/TRANSFER","MUHAMMAD NUR IMAN B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"22/09/2020","665417770001 | xx-5173 OCBC-AMK CENTRAL BR      S 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(290,"22/09/2020","from NASRIN FATIMAH | OTHR - FBM8473Z FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NASRIN FATIMAH","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(213,"23/09/2020","MUHAMMAD IRFAN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD IRFAN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD IRFAN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"23/09/2020","19/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","19/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"23/09/2020","19/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","19/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"23/09/2020","19/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","19/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(48.2,"23/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 22/09/2020 | POS SETTLEMENT","ATAN MOTOR","22/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(225,"23/09/2020","M I MUHAMMAD ABDULL | PAYMENT/TRANSFER OTHR S$ M I MUHAMMAD ABDULL via PayNow: FBK4960X | OTHR S$ | PAYMENT/TRANSFER","M I MUHAMMAD ABDULL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(5000,"23/09/2020","KAHARUDDIN BIN SAJA | PAYMENT/TRANSFER OTHR S$ KAHARUDDIN BIN SAJA via PayNow: FBK5878U | OTHR S$ | PAYMENT/TRANSFER","KAHARUDDIN BIN SAJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(53.5,"23/09/2020","MOHAMED ASIQ ALI S/ | PAYMENT/TRANSFER OTHR S$ MOHAMED ASIQ ALI S/ via PayNow: FBG650M | OTHR S$ | PAYMENT/TRANSFER","MOHAMED ASIQ ALI S/","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-27918.73,"23/09/2020","P2009SF6 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 27 918.73@1 Atan Motoring - 1st Oct Payment | CT0019613630 | FUND TRANSFER","P2009SF6","CT0019613630","FUND TRANSFER"));
        bankRecords.add(new Transaction(200,"23/09/2020","MUHAMMAD FAZREE BIN | SI                   F46L IBG GIRO | OTHR | IBG GIRO","MUHAMMAD FAZREE BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(245,"23/09/2020","LEE YEOU MING | SI                FBQ566X IBG GIRO | OTHR | IBG GIRO","LEE YEOU MING","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(328,"23/09/2020","HAMID BIN AHMAD | SI               FBQ2875R IBG GIRO | OTHR | IBG GIRO","HAMID BIN AHMAD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(210.45,"23/09/2020","THARIQ | PAYMENT/TRANSFER OTHR S$ THARIQ via PayNow: FBF6924P | OTHR S$ | PAYMENT/TRANSFER","THARIQ","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(98,"23/09/2020","ANG CHONG POH | PAYMENT/TRANSFER OTHR S$ ANG CHONG POH via PayNow: FBL9969J | OTHR S$ | PAYMENT/TRANSFER","ANG CHONG POH","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"23/09/2020","676534 | UOB BANK CHQ676534 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","676534","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(226.2,"23/09/2020","480361 | UOB BANK CHQ480361 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","480361","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(278,"23/09/2020","MOHAMMAD FARID BIN | PAYMENT/TRANSFER OTHR MOHAMMAD FARID BIN FBL8465C | OTHR | PAYMENT/TRANSFER","MOHAMMAD FARID BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-64.2,"23/09/2020","186300 | CHQ186300 CHEQUE |  | CHEQUE","186300","","CHEQUE"));
        bankRecords.add(new Transaction(463.37,"24/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 23/09/2020 | POS SETTLEMENT","ATAN MOTOR","23/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"24/09/2020","P200916 | GIRO CHARGES |  | GIRO CHARGES","P200916","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"24/09/2020","P200917 | GIRO CHARGES |  | GIRO CHARGES","P200917","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-353.1,"24/09/2020","P200916 | GIRO PAYMENT |  | GIRO PAYMENT","P200916","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-3405.51,"24/09/2020","P200917 | GIRO PAYMENT |  | GIRO PAYMENT","P200917","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-25,"24/09/2020","21/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","21/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-85.99,"24/09/2020","44096 | xx-8024 NTUC FAIRPRICE           S DEBIT PURCHASE |  | DEBIT PURCHASE","44096","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(333,"24/09/2020","from FBP2032L AKHFS | LOAR - FBP2032L (Aikal) FUND TRANSFER |  | FUND TRANSFER","from FBP2032L AKHFS","","FUND TRANSFER"));
        bankRecords.add(new Transaction(500,"24/09/2020","HEE WHYE TECK (XU W | PAYMENT/TRANSFER OTHR HEE WHYE TECK (XU W Transfer | OTHR | PAYMENT/TRANSFER","HEE WHYE TECK (XU W","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(235,"24/09/2020","RAHMAT BIN ASJADI | PAYMENT/TRANSFER OTHR S$ RAHMAT BIN ASJADI via PayNow: na | OTHR S$ | PAYMENT/TRANSFER","RAHMAT BIN ASJADI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(352,"24/09/2020","AMIR HAMZAH BIN ABD | PAYMENT/TRANSFER OTHR AMIR HAMZAH BIN ABD Transfer | OTHR | PAYMENT/TRANSFER","AMIR HAMZAH BIN ABD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(155,"24/09/2020","SEAH LI HOON | PAYMENT/TRANSFER OTHR SEAH LI HOON FBM3627E | OTHR | PAYMENT/TRANSFER","SEAH LI HOON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(319.25,"24/09/2020","MUHAMMAD AZLAN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AZLAN BIN via PayNow: FBN 7442 P | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AZLAN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"24/09/2020","IDROS BIN RAJAB ALI | PAYMENT/TRANSFER OTHR S$ IDROS BIN RAJAB ALI via PayNow: FBP8530B | OTHR S$ | PAYMENT/TRANSFER","IDROS BIN RAJAB ALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(395,"24/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(215,"24/09/2020","SRI LAKSHMI VINAYAG | PAYMENT/TRANSFER OTHR SRI LAKSHMI VINAYAG FBJ3475S | OTHR | PAYMENT/TRANSFER","SRI LAKSHMI VINAYAG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(549,"24/09/2020","from NUR'IFFAH ZAKI | OTHR - FBQ4411H FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NUR'IFFAH ZAKI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"24/09/2020","MUHAMMAD FIRDAUS BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FIRDAUS BI via PayNow: FBP898T | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FIRDAUS BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(170,"24/09/2020","SHAHRUL BIN ISMAIL | SI               FBR1251S IBG GIRO | OTHR | IBG GIRO","SHAHRUL BIN ISMAIL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(6.4,"24/09/2020","LOKE LAI PENG | PAYMENT/TRANSFER OTHR S$ LOKE LAI PENG via PayNow: Transfer - UEN | OTHR S$ | PAYMENT/TRANSFER","LOKE LAI PENG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"24/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"24/09/2020","NURUL ASHIKIN BINTE | PAYMENT/TRANSFER OTHR NURUL ASHIKIN BINTE FBF4230X | OTHR | PAYMENT/TRANSFER","NURUL ASHIKIN BINTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1000,"24/09/2020","TAN JI WEI | PAYMENT/TRANSFER OTHR S$ TAN JI WEI via PayNow: fbq8228u | OTHR S$ | PAYMENT/TRANSFER","TAN JI WEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(214,"25/09/2020","CHEONG SAI FHONG | PAYMENT/TRANSFER OTHR S$ CHEONG SAI FHONG via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","CHEONG SAI FHONG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(390,"25/09/2020","from FBM8540M MOHAM | OTHR - Fbm8540m FUND TRANSFER |  | FUND TRANSFER","from FBM8540M MOHAM","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-64,"25/09/2020","22/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","22/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(1014.5,"25/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 24/09/2020 | POS SETTLEMENT","ATAN MOTOR","24/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"25/09/2020","P200918 | GIRO CHARGES |  | GIRO CHARGES","P200918","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-20000,"25/09/2020","P200918 | GIRO PAYMENT |  | GIRO PAYMENT","P200918","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(356,"25/09/2020","from FBG6220G RIYAN | OTHR - FY9951T FUND TRANSFER |  | FUND TRANSFER","from FBG6220G RIYAN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(263.45,"25/09/2020","EMY HAZLEY BIN GHAZ | PAYMENT/TRANSFER OTHR EMY HAZLEY BIN GHAZ FBM3236A | OTHR | PAYMENT/TRANSFER","EMY HAZLEY BIN GHAZ","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(314,"25/09/2020","CARDUP.CO | PAYMENT/TRANSFER IVPT CARDUP.CO FBL4639U HP201610-0012 | IVPT | PAYMENT/TRANSFER","CARDUP.CO","IVPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(297,"25/09/2020","JEFFRY ZIN BIN SALL | PAYMENT/TRANSFER LOAN JEFFRY ZIN BIN SALL FBM358B JEFFRY ZIN BIN SALLEH | LOAN | PAYMENT/TRANSFER","JEFFRY ZIN BIN SALL","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(409,"25/09/2020","AL HALIM BIN KAMARU | SI               FBL9348B IBG GIRO | OTHR | IBG GIRO","AL HALIM BIN KAMARU","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(285,"25/09/2020","MOHD HAZEEM BIN ABD | SI               FBM1617Y IBG GIRO | OTHR | IBG GIRO","MOHD HAZEEM BIN ABD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(203,"25/09/2020","MUHAMMAD SHAH RIZAL | SI               FBP3909B IBG GIRO | OTHR | IBG GIRO","MUHAMMAD SHAH RIZAL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"25/09/2020","CHEW KUOK CHOON CLE | SI               FBL5211M IBG GIRO | OTHR | IBG GIRO","CHEW KUOK CHOON CLE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-72.4,"25/09/2020","SP SERVICES LTD | GIRO Collection 8903495888 IBG GIRO | COLL 8903495888 | IBG GIRO","SP SERVICES LTD","COLL 8903495888","IBG GIRO"));
        bankRecords.add(new Transaction(-36.88,"25/09/2020","SP SERVICES LTD | GIRO Collection 8902370199 IBG GIRO | COLL 8902370199 | IBG GIRO","SP SERVICES LTD","COLL 8902370199","IBG GIRO"));
        bankRecords.add(new Transaction(200,"25/09/2020","FATHUR ROSY BIN MAS | PAYMENT/TRANSFER OTHR FATHUR ROSY BIN MAS Transfer | OTHR | PAYMENT/TRANSFER","FATHUR ROSY BIN MAS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(356,"25/09/2020","AMIRRUDIN BIN AHMAD | PAYMENT/TRANSFER OTHR AMIRRUDIN BIN AHMAD FBN6494X | OTHR | PAYMENT/TRANSFER","AMIRRUDIN BIN AHMAD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(384,"25/09/2020","UTHIRAPATHY KAYALVI | PAYMENT/TRANSFER RENT UTHIRAPATHY KAYALVI Settled untill sep | RENT | PAYMENT/TRANSFER","UTHIRAPATHY KAYALVI","RENT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(271,"25/09/2020","NUR LYNDA BTE ABDUL | PAYMENT/TRANSFER OTHR NUR LYNDA BTE ABDUL Transfer | OTHR | PAYMENT/TRANSFER","NUR LYNDA BTE ABDUL","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"25/09/2020","MUHAMMAD KHAIRI BIN | PAYMENT/TRANSFER OTHR MUHAMMAD KHAIRI BIN fw8021u | OTHR | PAYMENT/TRANSFER","MUHAMMAD KHAIRI BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(295,"25/09/2020","252473 | RHB BANK BHD CHQ252473 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","252473","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-7383,"25/09/2020","186301 | CHQ186301 CHEQUE |  | CHEQUE","186301","","CHEQUE"));
        bankRecords.add(new Transaction(100,"26/09/2020","MUHAMMAD AL-FARIS B | PAYMENT/TRANSFER OTHR MUHAMMAD AL-FARIS B Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD AL-FARIS B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(613,"26/09/2020","MOHAMMAD NASIR BIN | PAYMENT/TRANSFER OTHR S$ MOHAMMAD NASIR BIN via PayNow: FBR2373S CB400X | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD NASIR BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(354,"26/09/2020","AZMEE BIN HAMZAH | PAYMENT/TRANSFER OTHR S$ AZMEE BIN HAMZAH via PayNow: FBQ4869X | OTHR S$ | PAYMENT/TRANSFER","AZMEE BIN HAMZAH","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(167,"26/09/2020","ASTIKA BIN ARHAM | PAYMENT/TRANSFER IHRP ASTIKA BIN ARHAM FBF9880L | IHRP | PAYMENT/TRANSFER","ASTIKA BIN ARHAM","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(277,"26/09/2020","from Zak ABDUL RAZA | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from Zak ABDUL RAZA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(235,"26/09/2020","CHAN SIEW MEI | PAYMENT/TRANSFER OTHR CHAN SIEW MEI FBL7318B | OTHR | PAYMENT/TRANSFER","CHAN SIEW MEI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(657,"26/09/2020","POON KAH ONN BENJAM | PAYMENT/TRANSFER OTHR S$ POON KAH ONN BENJAM via PayNow: FBA6154X | OTHR S$ | PAYMENT/TRANSFER","POON KAH ONN BENJAM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(419,"26/09/2020","NORKHISHAM BIN KAMS | PAYMENT/TRANSFER OTHR NORKHISHAM BIN KAMS Transfer | OTHR | PAYMENT/TRANSFER","NORKHISHAM BIN KAMS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(19.3,"26/09/2020","MUHD HADI BIN HALI | PAYMENT/TRANSFER OTHR S$ MUHD HADI BIN HALI via PayNow: FBL193P | OTHR S$ | PAYMENT/TRANSFER","MUHD HADI BIN HALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(251,"26/09/2020","MUHAMMAD SYAHMIE BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SYAHMIE BI via PayNow: fbn444e | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SYAHMIE BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(117,"28/09/2020","from S8422453G NORJ | OTHR - FBQ1405X FUND TRANSFER |  | FUND TRANSFER","from S8422453G NORJ","","FUND TRANSFER"));
        bankRecords.add(new Transaction(217,"28/09/2020","from S8422453G NORJ | OTHR - FBQ2241U FUND TRANSFER |  | FUND TRANSFER","from S8422453G NORJ","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-25,"28/09/2020","24/09/2020 |xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","24/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-64,"28/09/2020","24/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","24/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(281,"28/09/2020","MOHAMED NOH BIN BAS | PAYMENT/TRANSFER IHRP MOHAMED NOH BIN BAS FBM2040D | IHRP | PAYMENT/TRANSFER","MOHAMED NOH BIN BAS","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(210,"28/09/2020","MUHAMMAD AL-HAFIZ B | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AL-HAFIZ B via PayNow: Fx 35 c | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AL-HAFIZ B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(391,"28/09/2020","RAJA ZULFIKAAR ABDU | PAYMENT/TRANSFER OTHR S$ RAJA ZULFIKAAR ABDU via PayNow: FBQ8355K | OTHR S$ | PAYMENT/TRANSFER","RAJA ZULFIKAAR ABDU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(458,"28/09/2020","MOHAMED AJMEL BIN M | PAYMENT/TRANSFER OTHR MOHAMED AJMEL BIN M fba 2060j outstanding is cleared | OTHR | PAYMENT/TRANSFER","MOHAMED AJMEL BIN M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"28/09/2020","MUHAMMAD FAIZ TAJUD | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FAIZ TAJUD via PayNow: FBP3638L | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FAIZ TAJUD","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"28/09/2020","MUHAMMAD ZULHUSNI B | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZULHUSNI B via PayNow: FBQ4082Z | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZULHUSNI B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(355,"28/09/2020","OW YONG HOA LEON | PAYMENT/TRANSFER OTHR OW YONG HOA LEON Transfer | OTHR | PAYMENT/TRANSFER","OW YONG HOA LEON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(265,"28/09/2020","MOHAMMAD RIDZUAN SH | PAYMENT/TRANSFER OTHR MOHAMMAD RIDZUAN SH FBM811J | OTHR | PAYMENT/TRANSFER","MOHAMMAD RIDZUAN SH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"28/09/2020","SITI SHARIPAH BTE M | PAYMENT/TRANSFER IHRP SITI SHARIPAH BTE M mthly instalment for FBM2963R | IHRP | PAYMENT/TRANSFER","SITI SHARIPAH BTE M","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(248,"28/09/2020","SITI SHARIPAH BTE M | PAYMENT/TRANSFER IHRP SITI SHARIPAH BTE M mthly instalment for FBM4172T | IHRP | PAYMENT/TRANSFER","SITI SHARIPAH BTE M","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-64,"28/09/2020","25/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","25/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(50.25,"28/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 25/09/2020 | POS SETTLEMENT","ATAN MOTOR","25/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(2832.85,"28/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 26/09/2020 | POS SETTLEMENT","ATAN MOTOR","26/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-1.8,"28/09/2020","P200915 | GIRO CHARGES |  | GIRO CHARGES","P200915","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"28/09/2020","P200919 | GIRO CHARGES |  | GIRO CHARGES","P200919","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"28/09/2020","P200920 | GIRO CHARGES |  | GIRO CHARGES","P200920","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-25419,"28/09/2020","P200915 | GIRO PAYMENT |  | GIRO PAYMENT","P200915","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-2264.5,"28/09/2020","P200919 | GIRO PAYMENT |  | GIRO PAYMENT","P200919","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-110.46,"28/09/2020","P200920 | GIRO PAYMENT |  | GIRO PAYMENT","P200920","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-5214.04,"28/09/2020","JTC CORPORATION | SGGC200925192167 GIRO | COLL 1950008265 | GIRO","JTC CORPORATION","COLL 1950008265","GIRO"));
        bankRecords.add(new Transaction(260,"28/09/2020","FAIZAH BINTE MASIRA | PAYMENT/TRANSFER OTHR FAIZAH BINTE MASIRA FBP270D | OTHR | PAYMENT/TRANSFER","FAIZAH BINTE MASIRA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(850,"28/09/2020","FARHANAH BINTE ABDU | PAYMENT/TRANSFER OTHR FARHANAH BINTE ABDU FBM4118B | OTHR | PAYMENT/TRANSFER","FARHANAH BINTE ABDU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(335,"28/09/2020","from FBQ4766J SHAFI | OTHR - FBQ4766J insurance FUND TRANSFER |  | FUND TRANSFER","from FBQ4766J SHAFI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(265,"28/09/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(110,"28/09/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(248,"28/09/2020","CHONG YENN BIN | PAYMENT/TRANSFER OTHR S$ CHONG YENN BIN via PayNow: FBK5363M | OTHR S$ | PAYMENT/TRANSFER","CHONG YENN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(531,"28/09/2020","TAN YEAN CHERNG (CH | PAYMENT/TRANSFER OTHR TAN YEAN CHERNG (CH Transfer | OTHR | PAYMENT/TRANSFER","TAN YEAN CHERNG (CH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(16.05,"28/09/2020","MUHAMMAD ALIF BIN M | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ALIF BIN M via PayNow: FBP2400H | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ALIF BIN M","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(14000,"28/09/2020","LAM OEI LAI | PAYMENT/TRANSFER OTHR S$ LAM OEI LAI via PayNow: FBR7428K | OTHR S$ | PAYMENT/TRANSFER","LAM OEI LAI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(324,"28/09/2020","MUHAMMAD ISMAIL BIN | SI               FBM3288A IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ISMAIL BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(260,"28/09/2020","NORIDAH BINTE OTHMA | SI               FBP1819R IBG GIRO | OTHR | IBG GIRO","NORIDAH BINTE OTHMA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(198,"28/09/2020","SALBIAH BTE ATAN | SI               FBM7103Z IBG GIRO | OTHR | IBG GIRO","SALBIAH BTE ATAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(258,"28/09/2020","WONG VOON PIN | SI               FBQ6808J IBG GIRO | OTHR | IBG GIRO","WONG VOON PIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(276,"28/09/2020","MUHD SAHAFUDDIN B A | SI                FW4747K IBG GIRO | OTHR | IBG GIRO","MUHD SAHAFUDDIN B A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(353,"28/09/2020","MUHAMMAD ASYRAF BIN | SI               FBQ2820Z IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ASYRAF BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(209,"28/09/2020","RAJMOHAMED SHAHUL H | SI               FBQ8394Y IBG GIRO | OTHR | IBG GIRO","RAJMOHAMED SHAHUL H","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(275,"28/09/2020","HAJJAH ROSE MELATI | SI               FBL5738K IBG GIRO | OTHR | IBG GIRO","HAJJAH ROSE MELATI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(245,"28/09/2020","MOHAMMAD ZAILAN B B | SI               FBP8276K IBG GIRO | OTHR | IBG GIRO","MOHAMMAD ZAILAN B B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(437,"28/09/2020","WONG SIEW KEE | SI               FBM5844G IBG GIRO | OTHR | IBG GIRO","WONG SIEW KEE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(350,"28/09/2020","SIAH WOEI SHYANG | SI               FBM5529S IBG GIRO | OTHR | IBG GIRO","SIAH WOEI SHYANG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(207,"28/09/2020","MUHAMMAD ROSLEE AFI | SI               FBN8386L IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ROSLEE AFI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(274,"28/09/2020","SITI NUR EISSABELLA | SI                FBP726G IBG GIRO | OTHR | IBG GIRO","SITI NUR EISSABELLA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(296,"28/09/2020","LUQMAANUL-HAKEEM BI | SI                FBL694K IBG GIRO | OTHR | IBG GIRO","LUQMAANUL-HAKEEM BI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(518,"28/09/2020","MUHAMMAD QUSYAIRI B | SI                FN5115A IBG GIRO | OTHR | IBG GIRO","MUHAMMAD QUSYAIRI B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"28/09/2020","HASBULLAH BIN ABDUL | SI               FBL4649R IBG GIRO | OTHR | IBG GIRO","HASBULLAH BIN ABDUL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(282,"28/09/2020","MUHAMMAD JAMIL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD JAMIL BIN via PayNow: FBP3911T | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD JAMIL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(254,"28/09/2020","LEONG HOONG SHENG | PAYMENT/TRANSFER OTHR LEONG HOONG SHENG FBP9659H | OTHR | PAYMENT/TRANSFER","LEONG HOONG SHENG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-110.28,"28/09/2020","186362 | CHQ186362 CHEQUE |  | CHEQUE","186362","","CHEQUE"));
        bankRecords.add(new Transaction(-1150,"28/09/2020","186163 | CHQ186163 CHEQUE |  | CHEQUE","186163","","CHEQUE"));
        bankRecords.add(new Transaction(-1258.46,"28/09/2020","186296 | CHQ186296 CHEQUE |  | CHEQUE","186296","","CHEQUE"));
        bankRecords.add(new Transaction(244,"29/09/2020","MOHAMAD FAIZAL BIN | PAYMENT/TRANSFER OTHR S$ MOHAMAD FAIZAL BIN via PayNow: 7407b | OTHR S$ | PAYMENT/TRANSFER","MOHAMAD FAIZAL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1325.55,"29/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 28/09/2020 | POS SETTLEMENT","ATAN MOTOR","28/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(300,"29/09/2020","ZAINORA BTE ESA | PAYMENT/TRANSFER OTHR ZAINORA BTE ESA Transfer | OTHR | PAYMENT/TRANSFER","ZAINORA BTE ESA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(340,"29/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(284,"29/09/2020","from MOHAMAD SHAHRI | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MOHAMAD SHAHRI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"29/09/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(200,"29/09/2020","MUHAMMAD HIDAYAT BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HIDAYAT BI via PayNow: FBQ8470K | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HIDAYAT BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(360,"29/09/2020","MUHD ZAIDI BIN ABD | PAYMENT/TRANSFER OTHR MUHD ZAIDI BIN ABD FBL1111U | OTHR | PAYMENT/TRANSFER","MUHD ZAIDI BIN ABD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(342,"29/09/2020","NUR ZUHAIRAH BINTE | PAYMENT/TRANSFER OTHR S$ NUR ZUHAIRAH BINTE via PayNow: FBM8318P | OTHR S$ | PAYMENT/TRANSFER","NUR ZUHAIRAH BINTE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(171,"29/09/2020","MUHAMMAD HAIRULNIZA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIRULNIZA via PayNow: FBN2190Z. S7145947J | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIRULNIZA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"29/09/2020","LEONG KAM SOONG | PAYMENT/TRANSFER OTHR LEONG KAM SOONG Transfer | OTHR | PAYMENT/TRANSFER","LEONG KAM SOONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(27,"29/09/2020","LTA | LTAFBA291Y IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(3395.28,"29/09/2020","TOKIO MARINE INSURA | C1284761 IBG GIRO | OTHR | IBG GIRO","TOKIO MARINE INSURA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-30,"29/09/2020","LTA | LTAFBJ6039Z IBG GIRO | PADD 051108800008 | IBG GIRO","LTA","PADD 051108800008","IBG GIRO"));
        bankRecords.add(new Transaction(100,"29/09/2020","SABRINA BINTE SALIM | PAYMENT/TRANSFER OTHR SABRINA BINTE SALIM fbl679e | OTHR | PAYMENT/TRANSFER","SABRINA BINTE SALIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-7383,"29/09/2020","186303 | CHQ186303 CHEQUE |  | CHEQUE","186303","","CHEQUE"));
        bankRecords.add(new Transaction(355,"29/09/2020","TING CHIN KIONG | PAYMENT/TRANSFER OTHR TING CHIN KIONG FBN847B   S8466039F | OTHR | PAYMENT/TRANSFER","TING CHIN KIONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(94,"30/09/2020","from NG WENG LOON | LOAR - FBE7176E FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NG WENG LOON","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(251,"30/09/2020","from FBL3296G FARID | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBL3296G FARID","","FUND TRANSFER"));
        bankRecords.add(new Transaction(350,"30/09/2020","SZE WEI KIAT | PAYMENT/TRANSFER OTHR S$ SZE WEI KIAT via PayNow: Fw9186x | OTHR S$ | PAYMENT/TRANSFER","SZE WEI KIAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(204,"30/09/2020","NURUL KHAIRUNNISA B | PAYMENT/TRANSFER OTHR NURUL KHAIRUNNISA B fbp994Z | OTHR | PAYMENT/TRANSFER","NURUL KHAIRUNNISA B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1050,"30/09/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 29/09/2020 | POS SETTLEMENT","ATAN MOTOR","29/09/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-25,"30/09/2020","26/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","26/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"30/09/2020","26/09/2020 |xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","26/09/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(334,"30/09/2020","LIM CHENG SOON | PAYMENT/TRANSFER OTHR LIM CHENG SOON FbC4963E | OTHR | PAYMENT/TRANSFER","LIM CHENG SOON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(232,"30/09/2020","SURESH PILLAI SAMMU | PAYMENT/TRANSFER OTHR SURESH PILLAI SAMMU Transfer | OTHR | PAYMENT/TRANSFER","SURESH PILLAI SAMMU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(15,"30/09/2020","LOKE LAI PENG | PAYMENT/TRANSFER OTHR S$ LOKE LAI PENG via PayNow: Servicing | OTHR S$ | PAYMENT/TRANSFER","LOKE LAI PENG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1246,"30/09/2020","PANG WOEI CHUAN | PAYMENT/TRANSFER OTHR S$ PANG WOEI CHUAN via PayNow: FBR3152G | OTHR S$ | PAYMENT/TRANSFER","PANG WOEI CHUAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(275,"30/09/2020","ZAREENAH BEEVI D/O | PAYMENT/TRANSFER OTHR S$ ZAREENAH BEEVI D/O via PayNow: FBM9353E | OTHR S$ | PAYMENT/TRANSFER","ZAREENAH BEEVI D/O","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"30/09/2020","SHAUJIAH BINTE ROBA | PAYMENT/TRANSFER OTHR SHAUJIAH BINTE ROBA M Rashid FBH7436P | OTHR | PAYMENT/TRANSFER","SHAUJIAH BINTE ROBA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"30/09/2020","from MUHAMMAD SHAHR | OTHR - FBQ5657J FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MUHAMMAD SHAHR","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(361,"30/09/2020","MUHAMMAD RAMADHAN B | PAYMENT/TRANSFER OTHR MUHAMMAD RAMADHAN B FBN396K Installment | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAMADHAN B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(282,"30/09/2020","from FBJ6173T ZALAN | LOAR - FBJ6173T FUND TRANSFER |  | FUND TRANSFER","from FBJ6173T ZALAN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(438,"30/09/2020","ARIEF SUFIAN BIN ZU | PAYMENT/TRANSFER IHRP ARIEF SUFIAN BIN ZU Instalment of Ducati FBF5439E | IHRP | PAYMENT/TRANSFER","ARIEF SUFIAN BIN ZU","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(700,"30/09/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN Fbp1371p | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(241,"30/09/2020","MUHAMMAD YAZID BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD YAZID BIN via PayNow: FBL 8613 S - Insurance | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD YAZID BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(245,"30/09/2020","MUHAMMAD YAZID BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD YAZID BIN via PayNow: FBL 8613 S Installment | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD YAZID BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(432,"30/09/2020","LUI WAI LEONG | PAYMENT/TRANSFER OTHR S$ LUI WAI LEONG via PayNow: NRIC S7429750A | OTHR S$ | PAYMENT/TRANSFER","LUI WAI LEONG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(271,"30/09/2020","MUHAMMAD MAHMUD B M | PAYMENT/TRANSFER OTHR MUHAMMAD MAHMUD B M FBG1921A | OTHR | PAYMENT/TRANSFER","MUHAMMAD MAHMUD B M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(178,"30/09/2020","LTA | LTAFBA291Y IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(316,"30/09/2020","ABDUL HAKIM BIN TAJ | SI               FBQ3011K IBG GIRO | OTHR | IBG GIRO","ABDUL HAKIM BIN TAJ","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(236,"30/09/2020","WAN MUHAMMAD REZZAR | SI               FBF5622S IBG GIRO | OTHR | IBG GIRO","WAN MUHAMMAD REZZAR","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(267,"30/09/2020","WAN CHEE SENG | SI               FBP3179R IBG GIRO | OTHR | IBG GIRO","WAN CHEE SENG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(170,"30/09/2020","SAZALAN BIN ABDUL H | SI               FBH6208M IBG GIRO | OTHR | IBG GIRO","SAZALAN BIN ABDUL H","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(330,"30/09/2020","HUSAIN BIN MOHAMED | SI                FY9294G IBG GIRO | OTHR | IBG GIRO","HUSAIN BIN MOHAMED","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(226,"30/09/2020","LEE XIU PING | SI                FBQ982C IBG GIRO | OTHR | IBG GIRO","LEE XIU PING","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-57.99,"30/09/2020","Singapore Telecommu | 68472174 IBG GIRO | COLL 68472174 | IBG GIRO","Singapore Telecommu","COLL 68472174","IBG GIRO"));
        bankRecords.add(new Transaction(-421.27,"30/09/2020","Singapore Telecommu | 11538363 IBG GIRO | COLL 11538363 | IBG GIRO","Singapore Telecommu","COLL 11538363","IBG GIRO"));
        bankRecords.add(new Transaction(221,"30/09/2020","MARIANI BINTE ABDUL | PAYMENT/TRANSFER OTHR S$ MARIANI BINTE ABDUL via PayNow: FBK2944G | OTHR S$ | PAYMENT/TRANSFER","MARIANI BINTE ABDUL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(307,"30/09/2020","MOHAMMAD YAZID BIN | PAYMENT/TRANSFER OTHR MOHAMMAD YAZID BIN FX8752M | OTHR | PAYMENT/TRANSFER","MOHAMMAD YAZID BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(340,"30/09/2020","from KELVIN GOH KHA | OTHR - Fbr4259b FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from KELVIN GOH KHA","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(156,"30/09/2020","FATHUR RAHMAN BIN H | PAYMENT/TRANSFER OTHR S$ FATHUR RAHMAN BIN H via PayNow: FBC7693P | OTHR S$ | PAYMENT/TRANSFER","FATHUR RAHMAN BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(322,"30/09/2020","56903 | UOB BANK CHQ56903 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","56903","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(500,"30/09/2020","MUHAMMAD HAIKAL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIKAL BIN via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIKAL BIN","OTHR S$","PAYMENT/TRANSFER"));

    }

    public double getBankRecordsBalance(){

        statementClosingBalance = 0.00;
        try{
            for (Transaction record : bankRecords) {
                statementClosingBalance += record.transactionAmount;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return statementClosingBalance;
    }

}
