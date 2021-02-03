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
        bankRecords.add(new Transaction(5609,"01/12/2020","ISWANDA BIN PUJIYON | PAYMENT/TRANSFER OTHR S$ ISWANDA BIN PUJIYON via PayNow: FBG9596C | OTHR S$ | PAYMENT/TRANSFER","ISWANDA BIN PUJIYON","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"01/12/2020","MOHAMMAD ZAMEER BIN | PAYMENT/TRANSFER OTHR S$ MOHAMMAD ZAMEER BIN via PayNow: FBJ6122R | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD ZAMEER BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(278,"01/12/2020","HO ZHI QUAN | PAYMENT/TRANSFER OTHR HO ZHI QUAN FBK9357E | OTHR | PAYMENT/TRANSFER","HO ZHI QUAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(131,"01/12/2020","RIFQI NAZMI BIN ABD | PAYMENT/TRANSFER OTHR S$ RIFQI NAZMI BIN ABD via PayNow: FBJ832P | OTHR S$ | PAYMENT/TRANSFER","RIFQI NAZMI BIN ABD","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(204,"01/12/2020","NURUL KHAIRUNNISA B | PAYMENT/TRANSFER OTHR NURUL KHAIRUNNISA B fbp994z | OTHR | PAYMENT/TRANSFER","NURUL KHAIRUNNISA B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(661.65,"01/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 30/11/2020 | POS SETTLEMENT","ATAN MOTOR","30/11/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(251,"01/12/2020","from FBL3296G FARID | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBL3296G FARID","","FUND TRANSFER"));
        bankRecords.add(new Transaction(265,"01/12/2020","MOHAMED FEROZE BIN | PAYMENT/TRANSFER IHRP MOHAMED FEROZE BIN Feroze  FBN 401 D | IHRP | PAYMENT/TRANSFER","MOHAMED FEROZE BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(333,"01/12/2020","SUHAIRY BIN FADLILA | PAYMENT/TRANSFER OTHR S$ SUHAIRY BIN FADLILA via PayNow: FBR3022Z | OTHR S$ | PAYMENT/TRANSFER","SUHAIRY BIN FADLILA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"01/12/2020","from MUHAMMAD SHAHR | OTHR - FBQ5657J FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MUHAMMAD SHAHR","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(156,"01/12/2020","FATHUR RAHMAN BIN H | PAYMENT/TRANSFER OTHR S$ FATHUR RAHMAN BIN H via PayNow: FBC7693P | OTHR S$ | PAYMENT/TRANSFER","FATHUR RAHMAN BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"01/12/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN fbp1371p | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"01/12/2020","MUHAMMAD I'ZZUN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD I'ZZUN BIN via PayNow: FBN7299L | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD I'ZZUN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(307,"01/12/2020","SENG TSHUN MING | PAYMENT/TRANSFER LOAN SENG TSHUN MING Fbm1996c | LOAN | PAYMENT/TRANSFER","SENG TSHUN MING","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"01/12/2020","CHAN CHEE HONG | PAYMENT/TRANSFER OTHR CHAN CHEE HONG FBR2603R | OTHR | PAYMENT/TRANSFER","CHAN CHEE HONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(235,"01/12/2020","LEE KAH KUANG | PAYMENT/TRANSFER LOAN LEE KAH KUANG Transfer | LOAN | PAYMENT/TRANSFER","LEE KAH KUANG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(225,"01/12/2020","M I MUHAMMAD ABDULL | PAYMENT/TRANSFER OTHR S$ M I MUHAMMAD ABDULL via PayNow: FBK4960X | OTHR S$ | PAYMENT/TRANSFER","M I MUHAMMAD ABDULL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"01/12/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(171,"01/12/2020","MUHAMMAD HAIRULNIZA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIRULNIZA via PayNow: fbn2190z. S7145947J | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIRULNIZA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(360,"01/12/2020","DBS INTERNET BANKIN | FBL1111U IBG GIRO | OTHR | IBG GIRO","DBS INTERNET BANKIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(198,"01/12/2020","NOHAZLAN BIN ALI | SI               FBD6525T IBG GIRO | OTHR | IBG GIRO","NOHAZLAN BIN ALI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(208,"01/12/2020","MOHD HAMSANI B HAMD | SI               FBP6035C IBG GIRO | OTHR | IBG GIRO","MOHD HAMSANI B HAMD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(231,"01/12/2020","MOHD SHARIFFUDDIN B | SI               FBN2822J IBG GIRO | OTHR | IBG GIRO","MOHD SHARIFFUDDIN B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(281,"01/12/2020","CHEW KEAN NAM | SI               FBR5128P IBG GIRO | OTHR | IBG GIRO","CHEW KEAN NAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(284,"01/12/2020","from MOHAMAD SHAHRI | OTHR - Fbm6464m FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MOHAMAD SHAHRI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(219,"01/12/2020","ALIZAH BINTE ABDULL | PAYMENT/TRANSFER OTHR S$ ALIZAH BINTE ABDULL via PayNow: FBL4181Y | OTHR S$ | PAYMENT/TRANSFER","ALIZAH BINTE ABDULL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"01/12/2020","NORETA BINTE MD RAU | PAYMENT/TRANSFER OTHR S$ NORETA BINTE MD RAU via PayNow: Instalment for FBM7755 | OTHR S$ | PAYMENT/TRANSFER","NORETA BINTE MD RAU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(388,"01/12/2020","MUHAMMAD ZUHAIR JOH | PAYMENT/TRANSFER OTHR MUHAMMAD ZUHAIR JOH Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD ZUHAIR JOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(4050,"01/12/2020","893115 | OCBC CHQ893115 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","893115","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-1305.03,"01/12/2020","186441 | CHQ186441 CHEQUE |  | CHEQUE","186441","","CHEQUE"));
        bankRecords.add(new Transaction(438,"02/12/2020","HO CHI WING | PAYMENT/TRANSFER OTHR HO CHI WING FBG0332G | OTHR | PAYMENT/TRANSFER","HO CHI WING","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(372,"02/12/2020","GOH SIAU THIAN | PAYMENT/TRANSFER OTHR S$ GOH SIAU THIAN via PayNow: UEN198903552W FBP561P | OTHR S$ | PAYMENT/TRANSFER","GOH SIAU THIAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3433.5,"02/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 01/12/2020 | POS SETTLEMENT","ATAN MOTOR","01/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(298,"02/12/2020","NUR WANI BINTE MOHA | PAYMENT/TRANSFER OTHR NUR WANI BINTE MOHA Transfer FBN3736T | OTHR | PAYMENT/TRANSFER","NUR WANI BINTE MOHA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"02/12/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(220,"02/12/2020","QUAH CHEUN TAI | PAYMENT/TRANSFER OTHR S$ QUAH CHEUN TAI via PayNow: FBN2627G | OTHR S$ | PAYMENT/TRANSFER","QUAH CHEUN TAI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(256,"02/12/2020","from SEE YAO JIE RO | OTHR - NA FUND TRANSFER | via PayNow-QR Code | FUND TRANSFER","from SEE YAO JIE RO","via PayNow-QR Code","FUND TRANSFER"));
        bankRecords.add(new Transaction(147,"02/12/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(232,"02/12/2020","SURESH PILLAI SAMMU | PAYMENT/TRANSFER OTHR SURESH PILLAI SAMMU Transfer | OTHR | PAYMENT/TRANSFER","SURESH PILLAI SAMMU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(187,"02/12/2020","MOHD IZWAN BIN MOHD | PAYMENT/TRANSFER OTHR S$ MOHD IZWAN BIN MOHD via PayNow: FBM1276Y | OTHR S$ | PAYMENT/TRANSFER","MOHD IZWAN BIN MOHD","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(264,"02/12/2020","SIM LAI HOCK | SI               FBQ7119H IBG GIRO | OTHR | IBG GIRO","SIM LAI HOCK","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(198,"02/12/2020","SUFFIAN BIN MOHD NO | SI               FBJ1917Y IBG GIRO | OTHR | IBG GIRO","SUFFIAN BIN MOHD NO","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(520,"02/12/2020","JURAIMI BIN SAMRI | SI               FBL4574Y IBG GIRO | OTHR | IBG GIRO","JURAIMI BIN SAMRI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(392,"02/12/2020","ONG BOON HUAT | SI               FBR6999K IBG GIRO | OTHR | IBG GIRO","ONG BOON HUAT","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(216.7,"02/12/2020","ARMAN BIN MOHAMED A | SI               FBN8463Y IBG GIRO | OTHR | IBG GIRO","ARMAN BIN MOHAMED A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(264,"02/12/2020","MANAF BIN ABDULLAH | SI               FBJ6983Y IBG GIRO | OTHR | IBG GIRO","MANAF BIN ABDULLAH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(15.3,"02/12/2020","ARMAN BIN MOHAMED A | PAYMENT/TRANSFER OTHR S$ ARMAN BIN MOHAMED A via PayNow: FBN8463Y | OTHR S$ | PAYMENT/TRANSFER","ARMAN BIN MOHAMED A","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(234,"03/12/2020","MOHD SHAMSUDIN B MD | PAYMENT/TRANSFER OTHR MOHD SHAMSUDIN B MD FBL 6392 S | OTHR | PAYMENT/TRANSFER","MOHD SHAMSUDIN B MD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(296,"03/12/2020","CHEAH WENG LEONG | PAYMENT/TRANSFER OTHR CHEAH WENG LEONG FBQ5269X | OTHR | PAYMENT/TRANSFER","CHEAH WENG LEONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(561.8,"03/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 02/12/2020 | POS SETTLEMENT","ATAN MOTOR","02/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(150,"03/12/2020","from FBP1105S JAYA | OTHR - Dec FUND TRANSFER |  | FUND TRANSFER","from FBP1105S JAYA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(220,"03/12/2020","TAMIL SELVAM S/O G | PAYMENT/TRANSFER OTHR S$ TAMIL SELVAM S/O G via PayNow: FBK6328J | OTHR S$ | PAYMENT/TRANSFER","TAMIL SELVAM S/O G","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(166,"03/12/2020","NOR IRSHARUDDIN BIN | PAYMENT/TRANSFER OTHR NOR IRSHARUDDIN BIN FBC6854Z S9702411A | OTHR | PAYMENT/TRANSFER","NOR IRSHARUDDIN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"03/12/2020","MUHAMMAD KHAIRI BIN | PAYMENT/TRANSFER OTHR MUHAMMAD KHAIRI BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD KHAIRI BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(122,"03/12/2020","NUR RISHDA BINTE MO | PAYMENT/TRANSFER OTHR S$ NUR RISHDA BINTE MO via PayNow: FBG5928E | OTHR S$ | PAYMENT/TRANSFER","NUR RISHDA BINTE MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(185,"03/12/2020","from NORMALA BINTE | OTHR - FBQ9771K FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NORMALA BINTE","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(176,"03/12/2020","MOHAMED JAVED | PAYMENT/TRANSFER OTHR S$ MOHAMED JAVED via PayNow: FBH7745Y | OTHR S$ | PAYMENT/TRANSFER","MOHAMED JAVED","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(306,"03/12/2020","ATAN MOTOR | TRANSFER Fund Transfer from KSL AIRCONDITIONING & ENGINEERING W SGD 306.00 insuran | CT0020242199 | TRANSFER","ATAN MOTOR","CT0020242199","TRANSFER"));
        bankRecords.add(new Transaction(53.5,"03/12/2020","AXA INSURANCE PTE. | 33600WMKCSK IBG GIRO | OTHR | IBG GIRO","AXA INSURANCE PTE.","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-3171.22,"03/12/2020","P201201 | FUND TRANSFER Fund Transfer to account 652325689001 SGD 3 171.22@1 ATAN MOTORING - Oct 2020 | CT0020242905 | FUND TRANSFER","P201201","CT0020242905","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"03/12/2020","MUHAMMAD ZAFAPRI BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZAFAPRI BI via PayNow: 9971 | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZAFAPRI BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(150,"03/12/2020","WONG CHIN KEAT | PAYMENT/TRANSFER OTHR S$ WONG CHIN KEAT via PayNow: FBF7229G | OTHR S$ | PAYMENT/TRANSFER","WONG CHIN KEAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(306,"03/12/2020","LIM KOK HWA | PAYMENT/TRANSFER OTHR S$ LIM KOK HWA via PayNow: Lim Kok Hwa FBR1035A | OTHR S$ | PAYMENT/TRANSFER","LIM KOK HWA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(226.2,"03/12/2020","480374 | UOB BANK CHQ480374 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","480374","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(5000,"03/12/2020","987868 | CREDIT AGRICOLE C&I CHQ987868 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","987868","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(0.5,"03/12/2020"," | FAST Rebate SERVICE CHARGE ADJ | 1028675531 | SERVICE CHARGE ADJ","","1028675531","SERVICE CHARGE ADJ"));
        bankRecords.add(new Transaction(-25,"04/12/2020","01/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-74,"04/12/2020","01/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","01/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(2535.15,"04/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 03/12/2020 | POS SETTLEMENT","ATAN MOTOR","03/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(215,"04/12/2020","from MOHD SYAFIE BI | OTHR - FBQ8096H FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MOHD SYAFIE BI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(217,"04/12/2020","MOHAMMAD ALI BIN LA | PAYMENT/TRANSFER OTHR S$ MOHAMMAD ALI BIN LA via PayNow: FBK863U | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD ALI BIN LA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(172,"04/12/2020","CHEN TIAM JIAM | PAYMENT/TRANSFER OTHR S$ CHEN TIAM JIAM via PayNow: FOR FBD8296R | OTHR S$ | PAYMENT/TRANSFER","CHEN TIAM JIAM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(172,"04/12/2020","CHEN TIAM JIAM | PAYMENT/TRANSFER OTHR S$ CHEN TIAM JIAM via PayNow: FOR FBD8296R | OTHR S$ | PAYMENT/TRANSFER","CHEN TIAM JIAM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"04/12/2020","SABRINA BINTE SALIM | PAYMENT/TRANSFER OTHR SABRINA BINTE SALIM fbl679e | OTHR | PAYMENT/TRANSFER","SABRINA BINTE SALIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(303,"04/12/2020","SHAKYNA BINTE RAMLE | PAYMENT/TRANSFER OTHR SHAKYNA BINTE RAMLE FBN2823G and insurance | OTHR | PAYMENT/TRANSFER","SHAKYNA BINTE RAMLE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(150,"04/12/2020","MUHAMMAD NIZAM BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NIZAM BIN via PayNow: fbm2846x nizam | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NIZAM BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"04/12/2020","SIVAKUMAR A/L G RAM | SI               FBP7760K IBG GIRO | OTHR | IBG GIRO","SIVAKUMAR A/L G RAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(300,"04/12/2020","LIANG WAI KEAN | SI               FBQ7274S IBG GIRO | OTHR | IBG GIRO","LIANG WAI KEAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(467,"04/12/2020","GOH CHIEW LEONG | SI               FBC3952T IBG GIRO | OTHR | IBG GIRO","GOH CHIEW LEONG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-661.12,"04/12/2020","VISA GOLD | CREV00705101 IBG GIRO | OTHR 850012549516 | IBG GIRO","VISA GOLD","OTHR 850012549516","IBG GIRO"));
        bankRecords.add(new Transaction(8,"04/12/2020","SHAKYNA BINTE RAMLE | PAYMENT/TRANSFER OTHR SHAKYNA BINTE RAMLE Thanks and Sorry | OTHR | PAYMENT/TRANSFER","SHAKYNA BINTE RAMLE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(742.5,"04/12/2020","SUNDARESWARAR S/O P | PAYMENT/TRANSFER OTHR S$ SUNDARESWARAR S/O P via PayNow: FQ1098X | OTHR S$ | PAYMENT/TRANSFER","SUNDARESWARAR S/O P","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(195,"05/12/2020","SAHAADHEVAN S/O PAT | PAYMENT/TRANSFER OTHR S$ SAHAADHEVAN S/O PAT via PayNow: FBQ4954H | OTHR S$ | PAYMENT/TRANSFER","SAHAADHEVAN S/O PAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-48,"05/12/2020","02/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","02/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(123,"05/12/2020","from FBQ4411H NUR'I | OTHR - FBQ4411H FUND TRANSFER |  | FUND TRANSFER","from FBQ4411H NUR'I","","FUND TRANSFER"));
        bankRecords.add(new Transaction(147,"05/12/2020","SIAH SENG CHONG | PAYMENT/TRANSFER LOAN SIAH SENG CHONG FBM7218B Alex | LOAN | PAYMENT/TRANSFER","SIAH SENG CHONG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-19777.53,"05/12/2020","P2012SF1 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 19 777.53@1 Atan Motoring 11th Dec Payment | CT0020257286 | FUND TRANSFER","P2012SF1","CT0020257286","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"05/12/2020","MARIANI BINTE ABDUL | PAYMENT/TRANSFER OTHR S$ MARIANI BINTE ABDUL via PayNow: FBR8570A | OTHR S$ | PAYMENT/TRANSFER","MARIANI BINTE ABDUL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"05/12/2020","MOHAMAD BIN A MANAP | PAYMENT/TRANSFER OTHR S$ MOHAMAD BIN A MANAP via PayNow: fy7625z | OTHR S$ | PAYMENT/TRANSFER","MOHAMAD BIN A MANAP","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(205,"05/12/2020","MUHD HADI BIN HALI | PAYMENT/TRANSFER OTHR S$ MUHD HADI BIN HALI via PayNow: FBL193P instalment | OTHR S$ | PAYMENT/TRANSFER","MUHD HADI BIN HALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(187.2,"05/12/2020","MOHAMMED SHAFEERUDE | PAYMENT/TRANSFER OTHR S$ MOHAMMED SHAFEERUDE via PayNow: 198903552w | OTHR S$ | PAYMENT/TRANSFER","MOHAMMED SHAFEERUDE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"05/12/2020","MUHAMMAD HAIKAL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIKAL BIN via PayNow: FBB8315E | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIKAL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"05/12/2020","TAN KIAN HER (CHEN | PAYMENT/TRANSFER OTHR S$ TAN KIAN HER (CHEN via PayNow: Fbg2847a | OTHR S$ | PAYMENT/TRANSFER","TAN KIAN HER (CHEN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"07/12/2020","03/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","03/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"07/12/2020","03/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","03/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-149.18,"07/12/2020","44168 | xx-8008 M1LTD RECURRING        SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44168","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(110,"07/12/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(158,"07/12/2020","HANNAN HUZAIFI BIN | PAYMENT/TRANSFER OTHR S$ HANNAN HUZAIFI BIN via PayNow: Fbk7253j | OTHR S$ | PAYMENT/TRANSFER","HANNAN HUZAIFI BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(155,"07/12/2020","SEAH LI HOON | PAYMENT/TRANSFER OTHR SEAH LI HOON FBM3627 | OTHR | PAYMENT/TRANSFER","SEAH LI HOON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(10,"07/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(255,"07/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(-64,"07/12/2020","04/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","04/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(73.85,"07/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 04/12/2020 | POS SETTLEMENT","ATAN MOTOR","04/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(632.45,"07/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 05/12/2020 | POS SETTLEMENT","ATAN MOTOR","05/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-350,"07/12/2020","P201202 | GIRO PAYMENT |  | GIRO PAYMENT","P201202","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"07/12/2020","P201202 | GIRO CHARGES |  | GIRO CHARGES","P201202","","GIRO CHARGES"));
        bankRecords.add(new Transaction(330,"07/12/2020","MOHD ROSLAN B MARWA | PAYMENT/TRANSFER OTHR MOHD ROSLAN B MARWA Transfer | OTHR | PAYMENT/TRANSFER","MOHD ROSLAN B MARWA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(215,"07/12/2020","from AFK ALAN FROST | LOAR - FBN2826Z FUND TRANSFER |  | FUND TRANSFER","from AFK ALAN FROST","","FUND TRANSFER"));
        bankRecords.add(new Transaction(244,"07/12/2020","THANIUS BIN MUSNI | PAYMENT/TRANSFER OTHR THANIUS BIN MUSNI INSTALLMENT NO 44 FOR BIKE FBL8716 | OTHR | PAYMENT/TRANSFER","THANIUS BIN MUSNI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(199,"07/12/2020","from CHANG POO TECK | OTHR - PayNow Transfer FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from CHANG POO TECK","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"07/12/2020","NAZRE BIN ABU BAKAR | PAYMENT/TRANSFER OTHR S$ NAZRE BIN ABU BAKAR via PayNow: FBP9156S | OTHR S$ | PAYMENT/TRANSFER","NAZRE BIN ABU BAKAR","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"07/12/2020","MOHD ISA BIN ISMAIL | PAYMENT/TRANSFER OTHR MOHD ISA BIN ISMAIL Transfer | OTHR | PAYMENT/TRANSFER","MOHD ISA BIN ISMAIL","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(340,"07/12/2020","SARAVANAN NADARAJA | PAYMENT/TRANSFER OTHR SARAVANAN NADARAJA Transfer | OTHR | PAYMENT/TRANSFER","SARAVANAN NADARAJA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(484,"07/12/2020","INDRA SUKMA BIN BUH | PAYMENT/TRANSFER IHRP INDRA SUKMA BIN BUH FBP6139L- December 2020 | IHRP | PAYMENT/TRANSFER","INDRA SUKMA BIN BUH","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"07/12/2020","MUHAMMAD SUFIAN BIN | SI               FBL6449M IBG GIRO | OTHR | IBG GIRO","MUHAMMAD SUFIAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(250,"07/12/2020","BAHARIN BIN OSMAN | SI               FBQ8329L IBG GIRO | OTHR | IBG GIRO","BAHARIN BIN OSMAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(228,"07/12/2020","NORDIN BIN YUSOF | SI               FBP5784E IBG GIRO | OTHR | IBG GIRO","NORDIN BIN YUSOF","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(304,"07/12/2020","NORAZMI BIN RAMLI | SI               FBR7763S IBG GIRO | OTHR | IBG GIRO","NORAZMI BIN RAMLI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(242,"07/12/2020","RAJA AFIQAH QISTINA | SI               FBN5666Z IBG GIRO | OTHR | IBG GIRO","RAJA AFIQAH QISTINA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(226,"07/12/2020","MOHAMED HASDI BIN M | SI               FBQ7850D IBG GIRO | OTHR | IBG GIRO","MOHAMED HASDI BIN M","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(186,"07/12/2020","KASMANI BIN KAMIS | SI               FBM1760R IBG GIRO | OTHR | IBG GIRO","KASMANI BIN KAMIS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(200,"07/12/2020","MOHD SAFARUL HAFIF | SI               FBN3623K IBG GIRO | OTHR | IBG GIRO","MOHD SAFARUL HAFIF","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(326,"07/12/2020","LOW CHENG KEE | SI               FBR7166R IBG GIRO | OTHR | IBG GIRO","LOW CHENG KEE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(363,"07/12/2020","ISHAK BIN SUBARI | SI               FBN3663U IBG GIRO | OTHR | IBG GIRO","ISHAK BIN SUBARI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(280,"07/12/2020","GOH WEE PHANG BENJA | PAYMENT/TRANSFER OTHR S$ GOH WEE PHANG BENJA via PayNow: FBA6868U back trye | OTHR S$ | PAYMENT/TRANSFER","GOH WEE PHANG BENJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(304,"07/12/2020","LOH CHIANG LONG | PAYMENT/TRANSFER TRPT LOH CHIANG LONG FBQ8258H | TRPT | PAYMENT/TRANSFER","LOH CHIANG LONG","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(218,"07/12/2020","MOHD RAZALI BIN MOH | PAYMENT/TRANSFER OTHR MOHD RAZALI BIN MOH Transfer | OTHR | PAYMENT/TRANSFER","MOHD RAZALI BIN MOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(271.96,"08/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 07/12/2020 | POS SETTLEMENT","ATAN MOTOR","07/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-4669.6,"08/12/2020","P201205 | GIRO PAYMENT |  | GIRO PAYMENT","P201205","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-4683.64,"08/12/2020","P201206 | GIRO PAYMENT |  | GIRO PAYMENT","P201206","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"08/12/2020","P201205 | GIRO CHARGES |  | GIRO CHARGES","P201205","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"08/12/2020","P201206 | GIRO CHARGES |  | GIRO CHARGES","P201206","","GIRO CHARGES"));
        bankRecords.add(new Transaction(203,"08/12/2020","FARHANI SHEREEN BTE | PAYMENT/TRANSFER OTHR FARHANI SHEREEN BTE FBP1309X | OTHR | PAYMENT/TRANSFER","FARHANI SHEREEN BTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(160,"08/12/2020","MUHAMMAD FAIZ BIN H | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FAIZ BIN H via PayNow: Fw7324c 2nd instalment | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FAIZ BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(480,"08/12/2020","KHONG TECK GUAN | PAYMENT/TRANSFER OTHR S$ KHONG TECK GUAN via PayNow: FBR 7547A | OTHR S$ | PAYMENT/TRANSFER","KHONG TECK GUAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(209,"08/12/2020","NOOR HAFIZAH BTE MO | PAYMENT/TRANSFER OTHR S$ NOOR HAFIZAH BTE MO via PayNow: FT2792H | OTHR S$ | PAYMENT/TRANSFER","NOOR HAFIZAH BTE MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(2000,"08/12/2020","NURUL FARADILAH BTE | PAYMENT/TRANSFER OTHR NURUL FARADILAH BTE MOTORBIKE FBE5630Z | OTHR | PAYMENT/TRANSFER","NURUL FARADILAH BTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(218,"08/12/2020","JOYCE THAM SHAO YUN | SI               FBN8982R IBG GIRO | OTHR | IBG GIRO","JOYCE THAM SHAO YUN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(223,"08/12/2020","ABDUL AZIZ BIN ANSA | SI               FBJ8469C IBG GIRO | OTHR | IBG GIRO","ABDUL AZIZ BIN ANSA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-2117,"08/12/2020","DAIMLER FINANCIAL S | HP156344 IBG GIRO | COLL HP156344 | IBG GIRO","DAIMLER FINANCIAL S","COLL HP156344","IBG GIRO"));
        bankRecords.add(new Transaction(19.3,"08/12/2020","MUHD HADI BIN HALI | PAYMENT/TRANSFER OTHR S$ MUHD HADI BIN HALI via PayNow: FBL193P | OTHR S$ | PAYMENT/TRANSFER","MUHD HADI BIN HALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(235,"08/12/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(272,"09/12/2020","MUHAMMAD RAZMI BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAZMI BIN FBK6296S | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAZMI BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(2665.1,"09/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 08/12/2020 | POS SETTLEMENT","ATAN MOTOR","08/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(361,"09/12/2020","MOHD BAKHTIAR BIN U | PAYMENT/TRANSFER OTHR MOHD BAKHTIAR BIN U FBP3055P monthly installment | OTHR | PAYMENT/TRANSFER","MOHD BAKHTIAR BIN U","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(208,"09/12/2020","ONG YU YING (WANG Y | PAYMENT/TRANSFER GOVI ONG YU YING (WANG Y for fbn9331m | GOVI | PAYMENT/TRANSFER","ONG YU YING (WANG Y","GOVI","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(275,"09/12/2020","LAZARUS RAYMOND YON | PAYMENT/TRANSFER OTHR S$ LAZARUS RAYMOND YON via PayNow: Na | OTHR S$ | PAYMENT/TRANSFER","LAZARUS RAYMOND YON","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1198.8,"09/12/2020","HELFI BIN HERMAN | PAYMENT/TRANSFER OTHR S$ HELFI BIN HERMAN via PayNow: FBP2388K | OTHR S$ | PAYMENT/TRANSFER","HELFI BIN HERMAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(262,"09/12/2020","665417770001 | xx-6230 OCBC-TAMPINES BRANCH     S 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(252,"09/12/2020","from FBP4146D MUHAM | OTHR - FBP4146D FUND TRANSFER |  | FUND TRANSFER","from FBP4146D MUHAM","","FUND TRANSFER"));
        bankRecords.add(new Transaction(359,"09/12/2020","411937 | DBS BANK CHQ411937 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","411937","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-5.35,"09/12/2020","186444 | CHQ186444 CHEQUE |  | CHEQUE","186444","","CHEQUE"));
        bankRecords.add(new Transaction(-236,"09/12/2020","186445 | CHQ186445 CHEQUE |  | CHEQUE","186445","","CHEQUE"));
        bankRecords.add(new Transaction(226,"10/12/2020","KHAIRIL SHAZWAN B S | PAYMENT/TRANSFER OTHR S$ KHAIRIL SHAZWAN B S via PayNow: FBP6263K | OTHR S$ | PAYMENT/TRANSFER","KHAIRIL SHAZWAN B S","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(195,"10/12/2020","MOHAMMAD KHAIRUL AZ | PAYMENT/TRANSFER OTHR MOHAMMAD KHAIRUL AZ FBP6279R | OTHR | PAYMENT/TRANSFER","MOHAMMAD KHAIRUL AZ","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-32,"10/12/2020","07/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","07/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(352,"10/12/2020","VILLAFUERTE ARIEL D | PAYMENT/TRANSFER COLL VILLAFUERTE ARIEL D FBR 1665 G December 2020 | COLL | PAYMENT/TRANSFER","VILLAFUERTE ARIEL D","COLL","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"10/12/2020","from SRI NUR ASMA-U | OTHR - FBQ2362D - installm FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from SRI NUR ASMA-U","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(2999.04,"10/12/2020","MUHAMMAD NUR HAZWAN | PAYMENT/TRANSFER OTHR MUHAMMAD NUR HAZWAN FBG2736L remaining payment | OTHR | PAYMENT/TRANSFER","MUHAMMAD NUR HAZWAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-1076.82,"10/12/2020","P201207 | FUND TRANSFER Fund Transfer to account 510522089001 SGD 1 076.82@1 HSBC Payment | CT0020299409 | FUND TRANSFER","P201207","CT0020299409","FUND TRANSFER"));
        bankRecords.add(new Transaction(-117.7,"10/12/2020","P201209 | FUND TRANSFER Fund Transfer to account 601265671001 SGD 117.70@1 Atan Motoring Supply Pte Ltd - Nov 2020 | CT0020299426 | FUND TRANSFER","P201209","CT0020299426","FUND TRANSFER"));
        bankRecords.add(new Transaction(-41620.02,"10/12/2020","P201208 | FUND TRANSFER Fund Transfer to account 618591200001 SGD 41 620.02@1 Inv:1331088827  1331089047  1331089330  1331089374  SMCTA OCT 2020 | CT0020299427 | FUND TRANSFER","P201208","CT0020299427","FUND TRANSFER"));
        bankRecords.add(new Transaction(195,"10/12/2020","MOHAMMAD KHAIRY SHU | PAYMENT/TRANSFER OTHR MOHAMMAD KHAIRY SHU FBP6099T | OTHR | PAYMENT/TRANSFER","MOHAMMAD KHAIRY SHU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(193,"10/12/2020","FOREST CHILD CADRE | PAYMENT/TRANSFER SALA FOREST CHILD CADRE Nadiah bike installment | SALA | PAYMENT/TRANSFER","FOREST CHILD CADRE","SALA","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(399,"10/12/2020","MUHAMMAD NABIL BIN | PAYMENT/TRANSFER OTHR MUHAMMAD NABIL BIN Transfer for renewing insurance | OTHR | PAYMENT/TRANSFER","MUHAMMAD NABIL BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(184,"10/12/2020","V RAJENDRAN | PAYMENT/TRANSFER OTHR S$ V RAJENDRAN via PayNow: FBF9210X | OTHR S$ | PAYMENT/TRANSFER","V RAJENDRAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(19,"10/12/2020","LTA | LTAFBF860D IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(33,"10/12/2020","LTA | LTAFBK4107T IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(67,"10/12/2020","LTA | LTAFX594E IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(8,"10/12/2020","LTA | LTAFBF860D IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(454,"10/12/2020","LTA | LTAFX594E IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(270,"10/12/2020","YELEMALAI S/O SUBRA | SI               FBM6729A IBG GIRO | OTHR | IBG GIRO","YELEMALAI S/O SUBRA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(296,"10/12/2020","ABDUL KHALIQ MARICA | SI               FBM3473G IBG GIRO | OTHR | IBG GIRO","ABDUL KHALIQ MARICA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(214,"10/12/2020","MOHAMMAD RAMZAN | SI               FBP7651S IBG GIRO | OTHR | IBG GIRO","MOHAMMAD RAMZAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(164,"10/12/2020","MUHAMMAD ISKANDAR B | SI                FW5342M IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ISKANDAR B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(322,"10/12/2020","KAU YENG CHING | SI               FBP1031X IBG GIRO | OTHR | IBG GIRO","KAU YENG CHING","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(3156.67,"10/12/2020","TOKIO MARINE INSURA | C1289369 IBG GIRO | OTHR | IBG GIRO","TOKIO MARINE INSURA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-1350,"10/12/2020","HONG LEONG FINANCE | HLF-006 DUE 20201210 IBG GIRO | IHRP 38316717005680 | IBG GIRO","HONG LEONG FINANCE","IHRP 38316717005680","IBG GIRO"));
        bankRecords.add(new Transaction(195,"10/12/2020","SANATI BIN MOHAMED | PAYMENT/TRANSFER OTHR SANATI BIN MOHAMED FX 373B | OTHR | PAYMENT/TRANSFER","SANATI BIN MOHAMED","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(345,"10/12/2020","254254 | RHB BANK BHD CHQ254254 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","254254","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-8024,"10/12/2020","186448 | CHQ186448 CHEQUE |  | CHEQUE","186448","","CHEQUE"));
        bankRecords.add(new Transaction(266,"10/12/2020","from FBD7182L MUHAM | OTHR - FBP1782L FUND TRANSFER |  | FUND TRANSFER","from FBD7182L MUHAM","","FUND TRANSFER"));
        bankRecords.add(new Transaction(239,"11/12/2020","NURAISHAH BINTE HAS | PAYMENT/TRANSFER OTHR NURAISHAH BINTE HAS FBP0184U Md Nafee Sukiman | OTHR | PAYMENT/TRANSFER","NURAISHAH BINTE HAS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(311,"11/12/2020","MUHAMMAD IDRUS BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD IDRUS BIN via PayNow: FBL8595K RD TAX INSURA | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD IDRUS BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(269,"11/12/2020","ARUNKUMAR S/O ELANG | PAYMENT/TRANSFER OTHR S$ ARUNKUMAR S/O ELANG via PayNow: FBQ9294X | OTHR S$ | PAYMENT/TRANSFER","ARUNKUMAR S/O ELANG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"11/12/2020","08/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","08/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(727.61,"11/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 10/12/2020 | POS SETTLEMENT","ATAN MOTOR","10/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"11/12/2020","P201205 (2/2) | GIRO CHARGES |  | GIRO CHARGES","P201205 (2/2)","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"11/12/2020","P201210 | GIRO CHARGES |  | GIRO CHARGES","P201210","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"11/12/2020","P201211 | GIRO CHARGES |  | GIRO CHARGES","P201211","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"11/12/2020","P201212 | GIRO CHARGES |  | GIRO CHARGES","P201212","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-283.59,"11/12/2020","P201205 (2/2) | GIRO PAYMENT |  | GIRO PAYMENT","P201205 (2/2)","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-1174.2,"11/12/2020","P201210 | GIRO PAYMENT |  | GIRO PAYMENT","P201210","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-50000,"11/12/2020","P201211 | GIRO PAYMENT |  | GIRO PAYMENT","P201211","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-7303,"11/12/2020","P201212 | GIRO PAYMENT |  | GIRO PAYMENT","P201212","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(292,"11/12/2020","from Payment MUHAMA | TRPT - FBR659G FUND TRANSFER |  | FUND TRANSFER","from Payment MUHAMA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-18108.38,"11/12/2020","P201213 | FUND TRANSFER Fund Transfer to account 507076347001 SGD 18 108.38@1 Atan Motoring Supply Aug-Nov 2020 | CT0020307463 | FUND TRANSFER","P201213","CT0020307463","FUND TRANSFER"));
        bankRecords.add(new Transaction(270,"11/12/2020","GAURAV THAKUR | PAYMENT/TRANSFER OTHR GAURAV THAKUR FBR8537Y - bike instalement | OTHR | PAYMENT/TRANSFER","GAURAV THAKUR","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-345,"11/12/2020","254254 | RHB BANK BHD CHQ254254 RETURN CHEQUE Cheque Irregularly Drawn |  | RETURN CHEQUE","254254","","RETURN CHEQUE"));
        bankRecords.add(new Transaction(24484.71,"11/12/2020","B01 24142 | TRANSFER Fund Transfer from SINGAPURA FINANCE LTD SGD 24 484.71 B01 24142 | CT0020315893 | TRANSFER","B01 24142","CT0020315893","TRANSFER"));
        bankRecords.add(new Transaction(300,"11/12/2020","SABRINA BINTE SALIM | PAYMENT/TRANSFER OTHR SABRINA BINTE SALIM fbl679e | OTHR | PAYMENT/TRANSFER","SABRINA BINTE SALIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(228,"11/12/2020","PayLah| christina | PAYMENT/TRANSFER OTHR S$ PayLah| christina via PayNow: FBR8564T | OTHR S$ | PAYMENT/TRANSFER","PayLah| christina","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(292,"11/12/2020","KAVIARASAN A/L SELL | SI               FBN5471T IBG GIRO | OTHR | IBG GIRO","KAVIARASAN A/L SELL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(254,"11/12/2020","ISMAIL BIN MOHD JAI | PAYMENT/TRANSFER IHRP ISMAIL BIN MOHD JAI FBP2944E ISMAIL M JAI | IHRP | PAYMENT/TRANSFER","ISMAIL BIN MOHD JAI","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-775.57,"11/12/2020","186447 | CHQ186447 CHEQUE |  | CHEQUE","186447","","CHEQUE"));
        bankRecords.add(new Transaction(239,"12/12/2020","MUHAMMAD JAMIL BIN | PAYMENT/TRANSFER IHRP MUHAMMAD JAMIL BIN Transfer | IHRP | PAYMENT/TRANSFER","MUHAMMAD JAMIL BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(258,"12/12/2020","MOHAMED SHAHRIL BIN | PAYMENT/TRANSFER OTHR MOHAMED SHAHRIL BIN shahril fbq671a yamaha mt15 | OTHR | PAYMENT/TRANSFER","MOHAMED SHAHRIL BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(241,"12/12/2020","NUR AZAM BIN AB RAH | PAYMENT/TRANSFER OTHR NUR AZAM BIN AB RAH FBN8042D | OTHR | PAYMENT/TRANSFER","NUR AZAM BIN AB RAH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(207,"12/12/2020","MUHAMMAD SHUKOR BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SHUKOR BIN FBP9788T For month of Jan 2021 | OTHR | PAYMENT/TRANSFER","MUHAMMAD SHUKOR BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(273,"12/12/2020","MUHAMMAD NASSER BIN | PAYMENT/TRANSFER OTHR MUHAMMAD NASSER BIN Dec 2020 Instalment for FBR8629R | OTHR | PAYMENT/TRANSFER","MUHAMMAD NASSER BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(330,"12/12/2020","RIDZAL BIN BUANG | PAYMENT/TRANSFER OTHR RIDZAL BIN BUANG FBP6334P | OTHR | PAYMENT/TRANSFER","RIDZAL BIN BUANG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(349,"12/12/2020","YAP NENG HAU | PAYMENT/TRANSFER OTHR S$ YAP NENG HAU via PayNow: Fbj1531b | OTHR S$ | PAYMENT/TRANSFER","YAP NENG HAU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(214,"12/12/2020","LIM CHIN LOONG | PAYMENT/TRANSFER OTHR S$ LIM CHIN LOONG via PayNow: FBJ7848Z | OTHR S$ | PAYMENT/TRANSFER","LIM CHIN LOONG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(189,"12/12/2020","SAIFUL BAHRI BIN MD | PAYMENT/TRANSFER OTHR SAIFUL BAHRI BIN MD FBQ8326U - bike installment | OTHR | PAYMENT/TRANSFER","SAIFUL BAHRI BIN MD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(466,"12/12/2020","MUHAMMAD SHAH INDRA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SHAH INDRA via PayNow: FBF4280B nov dec 2020 | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SHAH INDRA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(260,"12/12/2020","NUR AZAM BIN AB RAH | PAYMENT/TRANSFER OTHR NUR AZAM BIN AB RAH FBN8042D | OTHR | PAYMENT/TRANSFER","NUR AZAM BIN AB RAH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(302.18,"12/12/2020","LIM FU JIANG | PAYMENT/TRANSFER OTHR S$ LIM FU JIANG via PayNow: FBP8110E | OTHR S$ | PAYMENT/TRANSFER","LIM FU JIANG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(318,"12/12/2020","MUHAMMAD ISKANDAR B | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ISKANDAR B via PayNow: NA | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ISKANDAR B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(66,"12/12/2020","HO YI DAN | PAYMENT/TRANSFER OTHR S$ HO YI DAN via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","HO YI DAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(268,"12/12/2020","ROSLIN HAZLINDA BIN | PAYMENT/TRANSFER OTHR ROSLIN HAZLINDA BIN FBM8192J | OTHR | PAYMENT/TRANSFER","ROSLIN HAZLINDA BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"12/12/2020","from DALAIL BIN ABU | LOAR - Thanks FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from DALAIL BIN ABU","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(216,"12/12/2020","MUHAMMAD ANDIKA BIN | PAYMENT/TRANSFER OTHR MUHAMMAD ANDIKA BIN FBP4314K | OTHR | PAYMENT/TRANSFER","MUHAMMAD ANDIKA BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(258,"12/12/2020","NUR MUHAMMAD FIRDAU | PAYMENT/TRANSFER OTHR S$ NUR MUHAMMAD FIRDAU via PayNow: Fbl 6793T | OTHR S$ | PAYMENT/TRANSFER","NUR MUHAMMAD FIRDAU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"12/12/2020","NOR HIDIAYATI BTE A | PAYMENT/TRANSFER OTHR NOR HIDIAYATI BTE A FBL8923Y | OTHR | PAYMENT/TRANSFER","NOR HIDIAYATI BTE A","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(479,"12/12/2020","from Dzulhilmy MOHA | OTHR - FBJ927A FUND TRANSFER |  | FUND TRANSFER","from Dzulhilmy MOHA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(20000,"12/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(9950,"12/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(9900,"12/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(9850,"12/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(258,"14/12/2020","MUHAMMAD NUR HAKEEM | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NUR HAKEEM via PayNow: FBQ7609K Dec | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NUR HAKEEM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"14/12/2020","10/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","10/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"14/12/2020","10/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","10/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"14/12/2020","10/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","10/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(307,"14/12/2020","665417770001 | 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(246,"14/12/2020","from FBK5286C SHARE | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBK5286C SHARE","","FUND TRANSFER"));
        bankRecords.add(new Transaction(363,"14/12/2020","AZMAN BIN YUSOF | PAYMENT/TRANSFER OTHR S$ AZMAN BIN YUSOF via PayNow: FBQ 373 K | OTHR S$ | PAYMENT/TRANSFER","AZMAN BIN YUSOF","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(226,"14/12/2020","RADIWATI BTE MOHAME | PAYMENT/TRANSFER OTHR RADIWATI BTE MOHAME FBR1534B | OTHR | PAYMENT/TRANSFER","RADIWATI BTE MOHAME","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(390,"14/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(267,"14/12/2020","MUHAMMAD AFIQ BIN A | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AFIQ BIN A via PayNow: FBP1376B | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AFIQ BIN A","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(185,"14/12/2020","RAZALI BIN JOHARI | PAYMENT/TRANSFER OTHR RAZALI BIN JOHARI Transfer | OTHR | PAYMENT/TRANSFER","RAZALI BIN JOHARI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(105,"14/12/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-33.55,"14/12/2020","44176 | xx-7992 HDB SEASON PARKING     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44176","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"14/12/2020","11/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","11/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"14/12/2020","11/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","11/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"14/12/2020","11/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","11/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"14/12/2020","11/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","11/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-7.15,"14/12/2020","44176 | xx-8024 SENGKANG POLYCLINIC    SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44176","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(325,"14/12/2020","from FBM9691D NASRI | OTHR - 29th FBM9691D FUND TRANSFER |  | FUND TRANSFER","from FBM9691D NASRI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-543,"14/12/2020","CPF | BIZ GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(74.94,"14/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 11/12/2020 | POS SETTLEMENT","ATAN MOTOR","11/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(844.25,"14/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 12/12/2020 | POS SETTLEMENT","ATAN MOTOR","12/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(2000,"14/12/2020","THE ACCOUNTANT GENE | 5003577674 GIRO | OTHR | GIRO","THE ACCOUNTANT GENE","OTHR","GIRO"));
        bankRecords.add(new Transaction(-0.2,"14/12/2020","P201203 | GIRO CHARGES |  | GIRO CHARGES","P201203","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-350,"14/12/2020","P201203 | GIRO PAYMENT |  | GIRO PAYMENT","P201203","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(180,"14/12/2020","HAZLIATI BINTE ABDU | PAYMENT/TRANSFER OTHR HAZLIATI BINTE ABDU FBK1608J | OTHR | PAYMENT/TRANSFER","HAZLIATI BINTE ABDU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(202,"14/12/2020","MUHAMMAD ASYRAF BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ASYRAF BIN via PayNow: FBL6182G | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ASYRAF BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(258,"14/12/2020","RAJA ZULFIKAAR ABDU | PAYMENT/TRANSFER OTHR S$ RAJA ZULFIKAAR ABDU via PayNow: FBQ8355K extend insura | OTHR S$ | PAYMENT/TRANSFER","RAJA ZULFIKAAR ABDU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(88.87,"14/12/2020","from # TAN HWA MENG | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from # TAN HWA MENG","","FUND TRANSFER"));
        bankRecords.add(new Transaction(174,"14/12/2020","MUHAMMAD SHAFIQ BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SHAFIQ BIN via PayNow: FBP782T | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SHAFIQ BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-16835.52,"14/12/2020","P2012SF2 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 16 835.52@1 Atan Motoring 21st Dec Payment | CT0020329482 | FUND TRANSFER","P2012SF2","CT0020329482","FUND TRANSFER"));
        bankRecords.add(new Transaction(1203,"14/12/2020","LTA | LTAFBK4107T IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(202,"14/12/2020","WAHAB BIN MOHD ISA | SI               FBP2238K IBG GIRO | OTHR | IBG GIRO","WAHAB BIN MOHD ISA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(301,"14/12/2020","YEOH AH FOCK | SI               FBQ8386X IBG GIRO | OTHR | IBG GIRO","YEOH AH FOCK","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(307,"14/12/2020","RITA ZAHARA BTE KAM | SI               FBN5441E IBG GIRO | OTHR | IBG GIRO","RITA ZAHARA BTE KAM","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(201,"14/12/2020","SHAHRUL ANDEKA BIN | SI               FBM1787P IBG GIRO | OTHR | IBG GIRO","SHAHRUL ANDEKA BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(363,"14/12/2020","TAN CHUN CHANG | SI               FBM8030T IBG GIRO | OTHR | IBG GIRO","TAN CHUN CHANG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(236,"14/12/2020","WONG CHEE SONG | SI               FBN7766E IBG GIRO | OTHR | IBG GIRO","WONG CHEE SONG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(300,"14/12/2020","KAMARIAH BINTE KAMS | SI               FBM2846X IBG GIRO | OTHR | IBG GIRO","KAMARIAH BINTE KAMS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(200,"14/12/2020","AMILDA DEWI BINTE A | SI               FBN9391P IBG GIRO | OTHR | IBG GIRO","AMILDA DEWI BINTE A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(261,"14/12/2020","ABDULLAH KHALESH KA | SI               FBP8163C IBG GIRO | OTHR | IBG GIRO","ABDULLAH KHALESH KA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(167,"14/12/2020","MUHAMMAD ADZLAN BIN | SI               FBP5843T IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ADZLAN BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"14/12/2020","MOHAMMAD FAREHIN BI | SI               FBN2209E IBG GIRO | OTHR | IBG GIRO","MOHAMMAD FAREHIN BI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(15,"14/12/2020","MUHAMMAD SYAHIN AZW | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SYAHIN AZW via PayNow: NA | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SYAHIN AZW","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(265,"14/12/2020","SUNDARESWARAR S/O P | PAYMENT/TRANSFER OTHR S$ SUNDARESWARAR S/O P via PayNow: 1098 super 4 | OTHR S$ | PAYMENT/TRANSFER","SUNDARESWARAR S/O P","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(345,"14/12/2020","254256 | RHB BANK BHD CHQ254256 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","254256","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(265,"14/12/2020","MUHAMMAD AMEEN BADE | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AMEEN BADE via PayNow: FBJ3942K | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AMEEN BADE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-4653,"14/12/2020","186174 | CHQ186174 CHEQUE |  | CHEQUE","186174","","CHEQUE"));
        bankRecords.add(new Transaction(198,"14/12/2020","from Moto shop ROSL | LOAR - FBN3213K FUND TRANSFER |  | FUND TRANSFER","from Moto shop ROSL","","FUND TRANSFER"));
        bankRecords.add(new Transaction(218,"15/12/2020","MOHAMED HISHAM BIN | PAYMENT/TRANSFER OTHR S$ MOHAMED HISHAM BIN via PayNow: Transfer - UEN | OTHR S$ | PAYMENT/TRANSFER","MOHAMED HISHAM BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(205,"15/12/2020","SULAIMAN BIN HASHIM | PAYMENT/TRANSFER OTHR S$ SULAIMAN BIN HASHIM via PayNow: fy2553e | OTHR S$ | PAYMENT/TRANSFER","SULAIMAN BIN HASHIM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(395,"15/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 14/12/2020 | POS SETTLEMENT","ATAN MOTOR","14/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(325,"15/12/2020","from ZAIM ZA'IM AL- | TRPT - FBM3243D FUND TRANSFER |  | FUND TRANSFER","from ZAIM ZA'IM AL-","","FUND TRANSFER"));
        bankRecords.add(new Transaction(278,"15/12/2020","HUTCHEON ANDREW JOH | PAYMENT/TRANSFER OTHR HUTCHEON ANDREW JOH FBL2071U | OTHR | PAYMENT/TRANSFER","HUTCHEON ANDREW JOH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"15/12/2020","SAIFUL AMIRIN BIN J | PAYMENT/TRANSFER OTHR SAIFUL AMIRIN BIN J Transfer | OTHR | PAYMENT/TRANSFER","SAIFUL AMIRIN BIN J","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(183,"15/12/2020","KAMSANI BIN MD AMIN | PAYMENT/TRANSFER OTHR S$ KAMSANI BIN MD AMIN via PayNow: NA | OTHR S$ | PAYMENT/TRANSFER","KAMSANI BIN MD AMIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3860,"15/12/2020","LEE CHIN HSI | PAYMENT/TRANSFER OTHR S$ LEE CHIN HSI via PayNow: atan motoring | OTHR S$ | PAYMENT/TRANSFER","LEE CHIN HSI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"15/12/2020","SARRAVANAN PATMANAT | PAYMENT/TRANSFER OTHR S$ SARRAVANAN PATMANAT via PayNow: fbq4588g | OTHR S$ | PAYMENT/TRANSFER","SARRAVANAN PATMANAT","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(6609.77,"15/12/2020","AXA INSURANCE PTE L | PAYMENT/TRANSFER OTHR AXA INSURANCE PTE L 58789107 | OTHR | PAYMENT/TRANSFER","AXA INSURANCE PTE L","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(10000,"15/12/2020","LEE CHIN HSI | PAYMENT/TRANSFER OTHR S$ LEE CHIN HSI via PayNow: Fbr9730z | OTHR S$ | PAYMENT/TRANSFER","LEE CHIN HSI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(310,"15/12/2020","ROBIAH BINTE HAMIDO | SI                FBK803T IBG GIRO | OTHR | IBG GIRO","ROBIAH BINTE HAMIDO","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(291,"15/12/2020","MUHAMMAD NABIL BIN | SI               FBK6972Z IBG GIRO | OTHR | IBG GIRO","MUHAMMAD NABIL BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(191.76,"15/12/2020","ALDREN MICHAEL KOH | SI               FBM4788S IBG GIRO | OTHR | IBG GIRO","ALDREN MICHAEL KOH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(241,"15/12/2020","RAMARAU A/L SIMMTHE | SI               FBP6774C IBG GIRO | OTHR | IBG GIRO","RAMARAU A/L SIMMTHE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(212,"15/12/2020","KHAJAA SHAS S/O YAB | SI               FBH5802D IBG GIRO | OTHR | IBG GIRO","KHAJAA SHAS S/O YAB","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(242,"15/12/2020","JOACHIM DAVID SATIS | SI               FBL6204C IBG GIRO | OTHR | IBG GIRO","JOACHIM DAVID SATIS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(300,"15/12/2020","TAN ZHI WEI JOSHUA | PAYMENT/TRANSFER OTHR S$ TAN ZHI WEI JOSHUA via PayNow: na | OTHR S$ | PAYMENT/TRANSFER","TAN ZHI WEI JOSHUA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(93.1,"15/12/2020","MUHAMMAD FARID BIN | PAYMENT/TRANSFER OTHR MUHAMMAD FARID BIN FX9324J | OTHR | PAYMENT/TRANSFER","MUHAMMAD FARID BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-200,"15/12/2020","186175 | CHQ186175 CHEQUE |  | CHEQUE","186175","","CHEQUE"));
        bankRecords.add(new Transaction(280,"16/12/2020","FARHANAH BINTE ABDU | PAYMENT/TRANSFER OTHR FARHANAH BINTE ABDU FBM4118B | OTHR | PAYMENT/TRANSFER","FARHANAH BINTE ABDU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(365.05,"16/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 15/12/2020 | POS SETTLEMENT","ATAN MOTOR","15/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(227,"16/12/2020","from FBL4428L HASLI | TRPT - Transport FUND TRANSFER |  | FUND TRANSFER","from FBL4428L HASLI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-3824.97,"16/12/2020","P2012SF3 | FUND TRANSFER Fund Transfer to account 501831564001 SGD 3 824.97@1 Blk: 22593  FBQ4588G F/S | CT0020349513 | FUND TRANSFER","P2012SF3","CT0020349513","FUND TRANSFER"));
        bankRecords.add(new Transaction(250,"16/12/2020","MOHAMED YUSSOFF BIN | PAYMENT/TRANSFER TRPT MOHAMED YUSSOFF BIN FBL1650E | TRPT | PAYMENT/TRANSFER","MOHAMED YUSSOFF BIN","TRPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(890,"16/12/2020","SHARINESWARI D/O MO | PAYMENT/TRANSFER OTHR S$ SHARINESWARI D/O MO via PayNow: FBK6219R | OTHR S$ | PAYMENT/TRANSFER","SHARINESWARI D/O MO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(85,"16/12/2020","SAIFUL BAHRI BIN MD | PAYMENT/TRANSFER OTHR SAIFUL BAHRI BIN MD insurance extension | OTHR | PAYMENT/TRANSFER","SAIFUL BAHRI BIN MD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(295,"16/12/2020","MOOVAANAN S/O KUNAS | SI               FBN3339G IBG GIRO | OTHR | IBG GIRO","MOOVAANAN S/O KUNAS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(461,"16/12/2020","MUHAMMAD SUFIAN BIN | PAYMENT/TRANSFER IHRP MUHAMMAD SUFIAN BIN FBR7774K | IHRP | PAYMENT/TRANSFER","MUHAMMAD SUFIAN BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(227,"16/12/2020","YAP LIP MAN | PAYMENT/TRANSFER OTHR S$ YAP LIP MAN via PayNow: fbq3495y | OTHR S$ | PAYMENT/TRANSFER","YAP LIP MAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(280,"16/12/2020","MUHAMMAD NUR ZARIF | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NUR ZARIF via PayNow: FBF9973C | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NUR ZARIF","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"16/12/2020","LIM KIAN HOU | PAYMENT/TRANSFER OTHR LIM KIAN HOU Transfer | OTHR | PAYMENT/TRANSFER","LIM KIAN HOU","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(210,"16/12/2020","PayLah| kelvinchong | PAYMENT/TRANSFER OTHR S$ PayLah| kelvinchong via PayNow: FBP9996J | OTHR S$ | PAYMENT/TRANSFER","PayLah| kelvinchong","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-637.6,"16/12/2020","186450 | CHQ186450 CHEQUE |  | CHEQUE","186450","","CHEQUE"));
        bankRecords.add(new Transaction(-7352,"16/12/2020","186451 | CHQ186451 CHEQUE |  | CHEQUE","186451","","CHEQUE"));
        bankRecords.add(new Transaction(-25,"17/12/2020","14/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","14/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-62,"17/12/2020","14/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","14/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-64,"17/12/2020","14/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","14/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(510.5,"17/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 16/12/2020 | POS SETTLEMENT","ATAN MOTOR","16/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-6291,"17/12/2020","CPF | BIZ GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(-1650,"17/12/2020","CPF | BFWL GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(350,"17/12/2020","SALAHEDDIN BIN SHAI | PAYMENT/TRANSFER IHRP SALAHEDDIN BIN SHAI FBQ4887T | IHRP | PAYMENT/TRANSFER","SALAHEDDIN BIN SHAI","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(197,"17/12/2020","GOH WEE PHANG BENJA | PAYMENT/TRANSFER OTHR S$ GOH WEE PHANG BENJA via PayNow: FBA6868U battery chang | OTHR S$ | PAYMENT/TRANSFER","GOH WEE PHANG BENJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(685,"17/12/2020","AHMAD SYAQEER BIN S | PAYMENT/TRANSFER OTHR S$ AHMAD SYAQEER BIN S via PayNow: FBN7941R | OTHR S$ | PAYMENT/TRANSFER","AHMAD SYAQEER BIN S","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(264,"17/12/2020","LIM ZONG WEI | SI               FBL6349T IBG GIRO | OTHR | IBG GIRO","LIM ZONG WEI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(170,"17/12/2020","ABDUL RAHIM BIN YUS | SI               FBH1648J IBG GIRO | OTHR | IBG GIRO","ABDUL RAHIM BIN YUS","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(300,"17/12/2020","NURUL ASHIKIN BINTE | PAYMENT/TRANSFER OTHR NURUL ASHIKIN BINTE FBF4230X | OTHR | PAYMENT/TRANSFER","NURUL ASHIKIN BINTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"17/12/2020","MOHAMED IRFAN ALI | PAYMENT/TRANSFER OTHR S$ MOHAMED IRFAN ALI via PayNow: FBJ7619T | OTHR S$ | PAYMENT/TRANSFER","MOHAMED IRFAN ALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1626.5,"17/12/2020","848911 | CITIBANK  NA CHQ848911 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","848911","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(130,"17/12/2020","676603 | UOB BANK CHQ676603 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","676603","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(240,"18/12/2020","from FBQ4766J SHAFI | OTHR - FBQ4766J FUND TRANSFER |  | FUND TRANSFER","from FBQ4766J SHAFI","","FUND TRANSFER"));
        bankRecords.add(new Transaction(290,"18/12/2020","from MUHAMMAD ZUHAI | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MUHAMMAD ZUHAI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(65,"18/12/2020","NAZIMA BINTE SHIEK | PAYMENT/TRANSFER OTHR S$ NAZIMA BINTE SHIEK via PayNow: Ins extension FBN9158Y | OTHR S$ | PAYMENT/TRANSFER","NAZIMA BINTE SHIEK","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(0.03,"18/12/2020"," | CASH REBATE CASH REBATE |  | CASH REBATE","","","CASH REBATE"));
        bankRecords.add(new Transaction(0.04,"18/12/2020"," | CASH REBATE CASH REBATE |  | CASH REBATE","","","CASH REBATE"));
        bankRecords.add(new Transaction(0.85,"18/12/2020"," | CASH REBATE CASH REBATE |  | CASH REBATE","","","CASH REBATE"));
        bankRecords.add(new Transaction(-25,"18/12/2020","15/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","15/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-62.95,"18/12/2020","44180 | xx-8008 M1LTD RECURRING        SIN DEBIT PURCHASE |  | DEBIT PURCHASE","44180","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(547.1,"18/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 17/12/2020 | POS SETTLEMENT","ATAN MOTOR","17/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-260,"18/12/2020","P201214 | FUND TRANSFER Fund Transfer to account 713007102001 SGD 260.00@1 Invoice: #2020-12-011 | CT0020370097 | FUND TRANSFER","P201214","CT0020370097","FUND TRANSFER"));
        bankRecords.add(new Transaction(144,"18/12/2020","MUHAMMAD MUZZAMMIL | PAYMENT/TRANSFER OTHR S$ MUHAMMAD MUZZAMMIL via PayNow: FBG8423A | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD MUZZAMMIL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"18/12/2020","POOBALAN LOGANATHAN | PAYMENT/TRANSFER IHRP POOBALAN LOGANATHAN Transfer | IHRP | PAYMENT/TRANSFER","POOBALAN LOGANATHAN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(231,"18/12/2020","ZULKIFLI BIN MOHAME | PAYMENT/TRANSFER LOAN ZULKIFLI BIN MOHAME FBL2565L | LOAN | PAYMENT/TRANSFER","ZULKIFLI BIN MOHAME","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(243.43,"18/12/2020","SRI LAKSHMI VINAYAG | PAYMENT/TRANSFER OTHR SRI LAKSHMI VINAYAG FBJ3475S | OTHR | PAYMENT/TRANSFER","SRI LAKSHMI VINAYAG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(221,"18/12/2020","LIM BOON LEONG | SI               FBN9177S IBG GIRO | OTHR | IBG GIRO","LIM BOON LEONG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(285,"18/12/2020","CHAWLA AMIT | SI               FBM1701L IBG GIRO | OTHR | IBG GIRO","CHAWLA AMIT","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(368,"18/12/2020","660224 | UOB BANK CHQ660224 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","660224","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-500,"18/12/2020","185933 | CHQ185933 CHEQUE |  | CHEQUE","185933","","CHEQUE"));
        bankRecords.add(new Transaction(434,"19/12/2020","from FBQ8185J HARUN | OTHR - FBQ8185J FUND TRANSFER |  | FUND TRANSFER","from FBQ8185J HARUN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(264,"19/12/2020","MUHAMMAD IZZAN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD IZZAN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD IZZAN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-199,"19/12/2020","16/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","16/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"19/12/2020","16/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","16/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"19/12/2020","16/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","16/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(187,"19/12/2020","ONG YU YING (WANG Y | PAYMENT/TRANSFER OTHR ONG YU YING (WANG Y fbn9331m | OTHR | PAYMENT/TRANSFER","ONG YU YING (WANG Y","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(187,"19/12/2020","ONG YU YING (WANG Y | PAYMENT/TRANSFER OTHR ONG YU YING (WANG Y fnb9331m | OTHR | PAYMENT/TRANSFER","ONG YU YING (WANG Y","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(379.85,"19/12/2020","WEE CHENG YAN | PAYMENT/TRANSFER OTHR S$ WEE CHENG YAN via PayNow: FBG5482Z | OTHR S$ | PAYMENT/TRANSFER","WEE CHENG YAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(354,"19/12/2020","MOHAMED HASHIM BIN | PAYMENT/TRANSFER OTHR MOHAMED HASHIM BIN FBM7628B -Irfan | OTHR | PAYMENT/TRANSFER","MOHAMED HASHIM BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(338,"19/12/2020","SUTAN NAZARUDIN ANI | PAYMENT/TRANSFER OTHR SUTAN NAZARUDIN ANI FBR8019B | OTHR | PAYMENT/TRANSFER","SUTAN NAZARUDIN ANI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(168,"19/12/2020","MOHAMAD EFFENDI B S | PAYMENT/TRANSFER OTHR MOHAMAD EFFENDI B S FBQ 4503B | OTHR | PAYMENT/TRANSFER","MOHAMAD EFFENDI B S","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"21/12/2020","17/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"21/12/2020","17/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"21/12/2020","17/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"21/12/2020","17/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-48,"21/12/2020","17/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","17/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(258,"21/12/2020","BAKHIT BIN MOHD SA' | PAYMENT/TRANSFER OTHR S$ BAKHIT BIN MOHD SA' via PayNow: FBP3430P | OTHR S$ | PAYMENT/TRANSFER","BAKHIT BIN MOHD SA'","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(346.55,"21/12/2020","MUHAMMAD SAHRIN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SAHRIN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD SAHRIN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(444,"21/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(355,"21/12/2020","AMSYAR DINIE BIN AB | PAYMENT/TRANSFER OTHR S$ AMSYAR DINIE BIN AB via PayNow: FR2184Z | OTHR S$ | PAYMENT/TRANSFER","AMSYAR DINIE BIN AB","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-17.1,"21/12/2020","18/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","18/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-32,"21/12/2020","18/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","18/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(418.45,"21/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 18/12/2020 | POS SETTLEMENT","ATAN MOTOR","18/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(2789.05,"21/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 19/12/2020 | POS SETTLEMENT","ATAN MOTOR","19/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-0.2,"21/12/2020","P201204 | GIRO CHARGES |  | GIRO CHARGES","P201204","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"21/12/2020","P201215 | GIRO CHARGES |  | GIRO CHARGES","P201215","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-350,"21/12/2020","P201204 | GIRO PAYMENT |  | GIRO PAYMENT","P201204","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-600,"21/12/2020","P201215 | GIRO PAYMENT |  | GIRO PAYMENT","P201215","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(110,"21/12/2020","FADIL BIN ABD RAHIM | PAYMENT/TRANSFER OTHR FADIL BIN ABD RAHIM Transfer | OTHR | PAYMENT/TRANSFER","FADIL BIN ABD RAHIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500,"21/12/2020","HEE WHYE TECK (XU W | PAYMENT/TRANSFER OTHR HEE WHYE TECK (XU W Transfer | OTHR | PAYMENT/TRANSFER","HEE WHYE TECK (XU W","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(2847.72,"21/12/2020","MOHAMMAD HASIF BIN | PAYMENT/TRANSFER OTHR MOHAMMAD HASIF BIN Transfer | OTHR | PAYMENT/TRANSFER","MOHAMMAD HASIF BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(374.5,"21/12/2020","MOHAMMAD HASIF BIN | PAYMENT/TRANSFER OTHR MOHAMMAD HASIF BIN Transfer | OTHR | PAYMENT/TRANSFER","MOHAMMAD HASIF BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(34833.57,"21/12/2020","B01 24209 | TRANSFER Fund Transfer from SINGAPURA FINANCE LTD SGD 34 833.57 B01 24209 | CT0020390120 | TRANSFER","B01 24209","CT0020390120","TRANSFER"));
        bankRecords.add(new Transaction(236,"21/12/2020","MUHAMMAD AIZAT BIN | SI               FBM4963C IBG GIRO | OTHR | IBG GIRO","MUHAMMAD AIZAT BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(185,"21/12/2020","TNG BOON CHAI | SI               FBM0392B IBG GIRO | OTHR | IBG GIRO","TNG BOON CHAI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(210,"21/12/2020","RAM PRAKASH | SI               FBN8194Z IBG GIRO | OTHR | IBG GIRO","RAM PRAKASH","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(267,"21/12/2020","MUHD NUUR SHIDDIQ B | SI                FBR219T IBG GIRO | OTHR | IBG GIRO","MUHD NUUR SHIDDIQ B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-238.21,"21/12/2020","NETS (S) Pte Ltd | F110001310 IBG GIRO | PADD EA4354 | IBG GIRO","NETS (S) Pte Ltd","PADD EA4354","IBG GIRO"));
        bankRecords.add(new Transaction(24.6,"21/12/2020","from NURDIANA BINTE | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NURDIANA BINTE","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(-370,"21/12/2020","185934 | CHQ185934 CHEQUE |  | CHEQUE","185934","","CHEQUE"));
        bankRecords.add(new Transaction(390,"22/12/2020","from FBM8540M MOHAM | OTHR - FBM8540M FUND TRANSFER |  | FUND TRANSFER","from FBM8540M MOHAM","","FUND TRANSFER"));
        bankRecords.add(new Transaction(1777.35,"22/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 21/12/2020 | POS SETTLEMENT","ATAN MOTOR","21/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(242,"22/12/2020","JESSICA JESSIE D/O | PAYMENT/TRANSFER OTHR S$ JESSICA JESSIE D/O via PayNow: FBP9042L December 20 | OTHR S$ | PAYMENT/TRANSFER","JESSICA JESSIE D/O","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(207,"22/12/2020","FUN GUO HAO | PAYMENT/TRANSFER OTHR FUN GUO HAO FBJ8167Z | OTHR | PAYMENT/TRANSFER","FUN GUO HAO","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(352,"22/12/2020","AMIR HAMZAH BIN ABD | PAYMENT/TRANSFER OTHR AMIR HAMZAH BIN ABD Transfer | OTHR | PAYMENT/TRANSFER","AMIR HAMZAH BIN ABD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(227,"22/12/2020","ALMALIK FAIZAL BIN | PAYMENT/TRANSFER OTHR S$ ALMALIK FAIZAL BIN via PayNow: FU156C | OTHR S$ | PAYMENT/TRANSFER","ALMALIK FAIZAL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(399,"22/12/2020","AZMAN THANI B ABD G | PAYMENT/TRANSFER OTHR AZMAN THANI B ABD G Insurance renewal for FBK7157D | OTHR | PAYMENT/TRANSFER","AZMAN THANI B ABD G","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"22/12/2020","665417770001 | xx-5173 OCBC-AMK CENTRAL BR      S 3RD PTY TRANSFER ATM |  | 3RD PTY TRANSFER ATM","665417770001","","3RD PTY TRANSFER ATM"));
        bankRecords.add(new Transaction(117.7,"22/12/2020","SUFFIAN BIN MOHD NO | PAYMENT/TRANSFER OTHR S$ SUFFIAN BIN MOHD NO via PayNow: FBJ1917Y | OTHR S$ | PAYMENT/TRANSFER","SUFFIAN BIN MOHD NO","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(308,"22/12/2020"," | CASH DEPOSIT |  | CASH DEPOSIT","","","CASH DEPOSIT"));
        bankRecords.add(new Transaction(362,"22/12/2020","JOACHIM DAVID SATIS | PAYMENT/TRANSFER INSU JOACHIM DAVID SATIS Transfer | INSU | PAYMENT/TRANSFER","JOACHIM DAVID SATIS","INSU","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(237,"22/12/2020","MUHAMMAD NAZRUL BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD NAZRUL BIN via PayNow: Fbm 7871p | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD NAZRUL BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(247,"22/12/2020","MUHAMMAD NUR IMAN B | PAYMENT/TRANSFER OTHR MUHAMMAD NUR IMAN B fbp8737y | OTHR | PAYMENT/TRANSFER","MUHAMMAD NUR IMAN B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(247,"22/12/2020","SYED THAHA AHMAD B | SI               FBM4180U IBG GIRO | OTHR | IBG GIRO","SYED THAHA AHMAD B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(162,"22/12/2020","ABDUL HAFIQ BIN ABD | SI               FBP3652R IBG GIRO | OTHR | IBG GIRO","ABDUL HAFIQ BIN ABD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(248,"22/12/2020","MOHD NOR HASSAN B A | SI               FBN9719D IBG GIRO | OTHR | IBG GIRO","MOHD NOR HASSAN B A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(240,"22/12/2020","NOOR AISYAH BINTE K | SI               FBM3499H IBG GIRO | OTHR | IBG GIRO","NOOR AISYAH BINTE K","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-53.5,"22/12/2020","186435 | CHQ186435 CHEQUE |  | CHEQUE","186435","","CHEQUE"));
        bankRecords.add(new Transaction(-133.96,"22/12/2020","186378 | CHQ186378 CHEQUE |  | CHEQUE","186378","","CHEQUE"));
        bankRecords.add(new Transaction(-1626.5,"22/12/2020","186453 | CHQ186453 CHEQUE |  | CHEQUE","186453","","CHEQUE"));
        bankRecords.add(new Transaction(-7352,"22/12/2020","186454 | CHQ186454 CHEQUE |  | CHEQUE","186454","","CHEQUE"));
        bankRecords.add(new Transaction(-7352,"22/12/2020","186455 | CHQ186455 CHEQUE |  | CHEQUE","186455","","CHEQUE"));
        bankRecords.add(new Transaction(230,"23/12/2020","MUHAMMAD ZULHUSNI B | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZULHUSNI B via PayNow: FBQ4082Z | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZULHUSNI B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(30,"23/12/2020","MUHAMMAD ZULHUSNI B | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZULHUSNI B via PayNow: Fbq4082z | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZULHUSNI B","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(420.55,"23/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 22/12/2020 | POS SETTLEMENT","ATAN MOTOR","22/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(150,"23/12/2020","SANGARAN SINNAKOLAN | PAYMENT/TRANSFER OTHR SANGARAN SINNAKOLAN Transfer | OTHR | PAYMENT/TRANSFER","SANGARAN SINNAKOLAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(12000,"23/12/2020"," | ST-ATM DEP OCBC-ION ORCHARD BRANCH CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(10000,"23/12/2020"," | ST-ATM DEP OCBC-ION ORCHARD BRANCH CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(6260.7,"23/12/2020"," | ST-ATM DEP OCBC-ION ORCHARD BRANCH CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(-0.07,"23/12/2020"," | ST-ATM DEP OCBC-ION ORCHARD BRANCH SVCCHG COIN DEPOSIT |  | SVCCHG COIN DEPOSIT","","","SVCCHG COIN DEPOSIT"));
        bankRecords.add(new Transaction(3358,"23/12/2020"," | ST-ATM DEP OCBC-ION ORCHARD BRANCH CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(268,"23/12/2020","MUHAMMAD ZULKHAIRI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD ZULKHAIRI via PayNow: FBG6838A | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD ZULKHAIRI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(184,"23/12/2020","OSNI BIN YUSOF | PAYMENT/TRANSFER OTHR S$ OSNI BIN YUSOF via PayNow: Fbl6215X insurance | OTHR S$ | PAYMENT/TRANSFER","OSNI BIN YUSOF","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(222,"23/12/2020","MOHAMED ASIQ ALI S/ | PAYMENT/TRANSFER OTHR S$ MOHAMED ASIQ ALI S/ via PayNow: FBG650M | OTHR S$ | PAYMENT/TRANSFER","MOHAMED ASIQ ALI S/","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(239,"23/12/2020","MUHAMMAD AZLAN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD AZLAN BIN via PayNow: FBN 7442 P | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD AZLAN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(300,"23/12/2020","NORETA BINTE MD RAU | PAYMENT/TRANSFER OTHR S$ NORETA BINTE MD RAU via PayNow: Instalmt for FBM7755S | OTHR S$ | PAYMENT/TRANSFER","NORETA BINTE MD RAU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(2000,"23/12/2020","LIM YEAN ANN | PAYMENT/TRANSFER OTHR S$ LIM YEAN ANN via PayNow: Transfer - UEN | OTHR S$ | PAYMENT/TRANSFER","LIM YEAN ANN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"23/12/2020","MUHAMMAD FAZREE BIN | SI                   F46L IBG GIRO | OTHR | IBG GIRO","MUHAMMAD FAZREE BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(328,"23/12/2020","HAMID BIN AHMAD | SI               FBQ2875R IBG GIRO | OTHR | IBG GIRO","HAMID BIN AHMAD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(245,"23/12/2020","LEE YEOU MING | SI                FBQ566X IBG GIRO | OTHR | IBG GIRO","LEE YEOU MING","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(242,"23/12/2020","MUHAMMAD ALI BIN HU | SI               FBN3333Y IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ALI BIN HU","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(268,"23/12/2020","AMILDA DEWI BINTE A | PAYMENT/TRANSFER OTHR S$ AMILDA DEWI BINTE A via PayNow: RenewaLInsuranceFBN939 | OTHR S$ | PAYMENT/TRANSFER","AMILDA DEWI BINTE A","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(450000,"23/12/2020","605584 | UOB BANK CHQ605584 CHEQUE DEPOSIT |  | CHEQUE DEPOSIT","605584","","CHEQUE DEPOSIT"));
        bankRecords.add(new Transaction(-10641.2,"23/12/2020","186449 | CHQ186449 CHEQUE |  | CHEQUE","186449","","CHEQUE"));
        bankRecords.add(new Transaction(-22536.02,"23/12/2020","186379 | CHQ186379 CHEQUE |  | CHEQUE","186379","","CHEQUE"));
        bankRecords.add(new Transaction(356,"24/12/2020","from FBG6220G RIYAN | OTHR - FY9951T FUND TRANSFER |  | FUND TRANSFER","from FBG6220G RIYAN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(-25,"24/12/2020","21/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","21/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-17.1,"24/12/2020","21/12/2020 | xx-8024 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","21/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(3191.97,"24/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 23/12/2020 | POS SETTLEMENT","ATAN MOTOR","23/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(235,"24/12/2020","CHAN SIEW MEI | PAYMENT/TRANSFER OTHR CHAN SIEW MEI FBL7318B | OTHR | PAYMENT/TRANSFER","CHAN SIEW MEI","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(333,"24/12/2020","from FBP2032L AKHFS | LOAR - FBP2032L (Haikal) FUND TRANSFER |  | FUND TRANSFER","from FBP2032L AKHFS","","FUND TRANSFER"));
        bankRecords.add(new Transaction(3.23,"24/12/2020","from Kenneth TAN YO | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from Kenneth TAN YO","","FUND TRANSFER"));
        bankRecords.add(new Transaction(281,"24/12/2020","MOHAMED RAFEE BIN A | PAYMENT/TRANSFER OTHR MOHAMED RAFEE BIN A Transfer | OTHR | PAYMENT/TRANSFER","MOHAMED RAFEE BIN A","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(531,"24/12/2020","TAN YEAN CHERNG (CH | PAYMENT/TRANSFER OTHR TAN YEAN CHERNG (CH fbm1400 | OTHR | PAYMENT/TRANSFER","TAN YEAN CHERNG (CH","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(23.55,"24/12/2020","PayLah| Adam Toh | PAYMENT/TRANSFER OTHR S$ PayLah| Adam Toh via PayNow: FBH4251A | OTHR S$ | PAYMENT/TRANSFER","PayLah| Adam Toh","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(314,"24/12/2020","CARDUP.CO | PAYMENT/TRANSFER IVPT CARDUP.CO FBL4639U HP201610-0012 | IVPT | PAYMENT/TRANSFER","CARDUP.CO","IVPT","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-200000,"24/12/2020","P201217 | FUND TRANSFER Fund Transfer to account 5490003018 SGD 200 000.00@1 Return director loan | CT0020428200 | FUND TRANSFER","P201217","CT0020428200","FUND TRANSFER"));
        bankRecords.add(new Transaction(354,"24/12/2020","AZMEE BIN HAMZAH | PAYMENT/TRANSFER OTHR S$ AZMEE BIN HAMZAH via PayNow: FBQ4869X | OTHR S$ | PAYMENT/TRANSFER","AZMEE BIN HAMZAH","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(3093.58,"24/12/2020","LIANG WAI KEAN | PAYMENT/TRANSFER OTHR LIANG WAI KEAN Transfer | OTHR | PAYMENT/TRANSFER","LIANG WAI KEAN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(170,"24/12/2020","SHAHRUL BIN ISMAIL | SI               FBR1251S IBG GIRO | OTHR | IBG GIRO","SHAHRUL BIN ISMAIL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(250,"24/12/2020","MUHAMMAD RAYMOND BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD RAYMOND BI via PayNow: FBR9286P | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD RAYMOND BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-7753,"24/12/2020","186458 | CHQ186458 CHEQUE |  | CHEQUE","186458","","CHEQUE"));
        bankRecords.add(new Transaction(260,"26/12/2020","SITI SHARIPAH BTE M | PAYMENT/TRANSFER IHRP SITI SHARIPAH BTE M mthly instalment for FBM2963R | IHRP | PAYMENT/TRANSFER","SITI SHARIPAH BTE M","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(248,"26/12/2020","SITI SHARIPAH BTE M | PAYMENT/TRANSFER IHRP SITI SHARIPAH BTE M mthly instalment for FBM4172T | IHRP | PAYMENT/TRANSFER","SITI SHARIPAH BTE M","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(214,"26/12/2020","CHEONG SAI FHONG | PAYMENT/TRANSFER OTHR S$ CHEONG SAI FHONG via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","CHEONG SAI FHONG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(356,"26/12/2020","AMIRRUDIN BIN AHMAD | PAYMENT/TRANSFER OTHR AMIRRUDIN BIN AHMAD FBN6494X | OTHR | PAYMENT/TRANSFER","AMIRRUDIN BIN AHMAD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(254,"26/12/2020","LEONG HOONG SHENG | PAYMENT/TRANSFER OTHR LEONG HOONG SHENG FBP9659H | OTHR | PAYMENT/TRANSFER","LEONG HOONG SHENG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(263.45,"26/12/2020","EMY HAZLEY BIN GHAZ | PAYMENT/TRANSFER OTHR EMY HAZLEY BIN GHAZ FBM 3236 A | OTHR | PAYMENT/TRANSFER","EMY HAZLEY BIN GHAZ","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(419,"26/12/2020","NORKHISHAM BIN KAMS | PAYMENT/TRANSFER OTHR NORKHISHAM BIN KAMS Transfer | OTHR | PAYMENT/TRANSFER","NORKHISHAM BIN KAMS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"26/12/2020","TAN JI WEI | PAYMENT/TRANSFER OTHR S$ TAN JI WEI via PayNow: fbq8228u | OTHR S$ | PAYMENT/TRANSFER","TAN JI WEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(156,"26/12/2020","FATHUR RAHMAN BIN H | PAYMENT/TRANSFER OTHR S$ FATHUR RAHMAN BIN H via PayNow: FBC7693P | OTHR S$ | PAYMENT/TRANSFER","FATHUR RAHMAN BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(200,"26/12/2020","FATHUR ROSY BIN MAS | PAYMENT/TRANSFER OTHR FATHUR ROSY BIN MAS FW4928E December | OTHR | PAYMENT/TRANSFER","FATHUR ROSY BIN MAS","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"26/12/2020","ASHLEY ANIL SARAN | PAYMENT/TRANSFER OTHR S$ ASHLEY ANIL SARAN via PayNow: FBR9262H | OTHR S$ | PAYMENT/TRANSFER","ASHLEY ANIL SARAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(395,"26/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(271,"26/12/2020","NUR LYNDA BTE ABDUL | PAYMENT/TRANSFER OTHR NUR LYNDA BTE ABDUL Transfer | OTHR | PAYMENT/TRANSFER","NUR LYNDA BTE ABDUL","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"26/12/2020","23/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","23/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"26/12/2020","23/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","23/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(160,"26/12/2020","MUHAMMAD FAIZ BIN H | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FAIZ BIN H via PayNow: Fw7324c 3rd instalment | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FAIZ BIN H","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(214,"26/12/2020","NUR'ATIKAH BINTE MO | PAYMENT/TRANSFER OTHR NUR'ATIKAH BINTE MO Transfer | OTHR | PAYMENT/TRANSFER","NUR'ATIKAH BINTE MO","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(297,"26/12/2020","JEFFRY ZIN BIN SALL | PAYMENT/TRANSFER LOAN JEFFRY ZIN BIN SALL FBM358B JEFFRY ZIN BIN SALLEH | LOAN | PAYMENT/TRANSFER","JEFFRY ZIN BIN SALL","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"26/12/2020","MUHAMMAD I'ZZUN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD I'ZZUN BIN via PayNow: FBN7299L  JAN 2021 | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD I'ZZUN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(302,"26/12/2020","TAN YEE SENG | PAYMENT/TRANSFER LOAN TAN YEE SENG FBR 8878 M | LOAN | PAYMENT/TRANSFER","TAN YEE SENG","LOAN","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(277,"26/12/2020","from Zak ABDUL RAZA | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from Zak ABDUL RAZA","","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"26/12/2020","MUHAMMAD AZMI HAKIM | PAYMENT/TRANSFER OTHR MUHAMMAD AZMI HAKIM FBM7287B | OTHR | PAYMENT/TRANSFER","MUHAMMAD AZMI HAKIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(391,"26/12/2020","RAJA ZULFIKAAR ABDU | PAYMENT/TRANSFER OTHR S$ RAJA ZULFIKAAR ABDU via PayNow: FBQ8355K | OTHR S$ | PAYMENT/TRANSFER","RAJA ZULFIKAAR ABDU","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"28/12/2020","24/12/2020 | xx-7992 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","24/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(197,"28/12/2020","from NG WENG LOON | LOAR - FBE7176E FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NG WENG LOON","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(230,"28/12/2020","MUHAMMAD FAIZ TAJUD | PAYMENT/TRANSFER OTHR S$ MUHAMMAD FAIZ TAJUD via PayNow: FBP3628L | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD FAIZ TAJUD","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(100,"28/12/2020","SABRINA BINTE SALIM | PAYMENT/TRANSFER OTHR SABRINA BINTE SALIM FBL679E | OTHR | PAYMENT/TRANSFER","SABRINA BINTE SALIM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(251,"28/12/2020","MUHAMMAD SYAHMIE BI | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SYAHMIE BI via PayNow: FBN444E | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SYAHMIE BI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(171,"28/12/2020","MUHAMMAD HAIRULNIZA | PAYMENT/TRANSFER OTHR S$ MUHAMMAD HAIRULNIZA via PayNow: FBN2190Z. S7145947J. | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD HAIRULNIZA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(755,"28/12/2020","KAHARUDDIN BIN SAJA | PAYMENT/TRANSFER OTHR S$ KAHARUDDIN BIN SAJA via PayNow: FBK5878U | OTHR S$ | PAYMENT/TRANSFER","KAHARUDDIN BIN SAJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(50.3,"28/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 24/12/2020 | POS SETTLEMENT","ATAN MOTOR","24/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-3202.28,"28/12/2020","JTC CORPORATION | SGGC201224230450 GIRO | COLL 1950008265 | GIRO","JTC CORPORATION","COLL 1950008265","GIRO"));
        bankRecords.add(new Transaction(-1.8,"28/12/2020","P201216 | GIRO CHARGES |  | GIRO CHARGES","P201216","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-1.8,"28/12/2020","B2020 | GIRO CHARGES |  | GIRO CHARGES","B2020","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-0.2,"28/12/2020","P201218 | GIRO CHARGES |  | GIRO CHARGES","P201218","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-25369,"28/12/2020","P201216 | GIRO PAYMENT |  | GIRO PAYMENT","P201216","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-25679,"28/12/2020","B2020 | GIRO PAYMENT |  | GIRO PAYMENT","B2020","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-1393.57,"28/12/2020","P201218 | GIRO PAYMENT |  | GIRO PAYMENT","P201218","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(260,"28/12/2020","FAIZAH BINTE MASIRA | PAYMENT/TRANSFER OTHR FAIZAH BINTE MASIRA FBP270D | OTHR | PAYMENT/TRANSFER","FAIZAH BINTE MASIRA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(131,"28/12/2020","MUHAMMAD BIN HUSSIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD BIN HUSSIN via PayNow: FBG 6220G | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD BIN HUSSIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(221,"28/12/2020","OOI WOOI HIM | PAYMENT/TRANSFER OTHR S$ OOI WOOI HIM via PayNow: Dec loan payment | OTHR S$ | PAYMENT/TRANSFER","OOI WOOI HIM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(330.32,"28/12/2020","ASTIKA BIN ARHAM | PAYMENT/TRANSFER IHRP ASTIKA BIN ARHAM FBF9880L | IHRP | PAYMENT/TRANSFER","ASTIKA BIN ARHAM","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(378,"28/12/2020","NORHUDAH BINTE ISMA | PAYMENT/TRANSFER OTHR S$ NORHUDAH BINTE ISMA via PayNow: FBR9273B CB400XA INSTA | OTHR S$ | PAYMENT/TRANSFER","NORHUDAH BINTE ISMA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(30,"28/12/2020","NADIAH BINTE SAIRON | PAYMENT/TRANSFER OTHR S$ NADIAH BINTE SAIRON via PayNow: FBP9517J | OTHR S$ | PAYMENT/TRANSFER","NADIAH BINTE SAIRON","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(232,"28/12/2020","SHAKYNA BINTE RAMLE | PAYMENT/TRANSFER OTHR SHAKYNA BINTE RAMLE FBN2823G | OTHR | PAYMENT/TRANSFER","SHAKYNA BINTE RAMLE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(175,"28/12/2020","CHONG YEN MEI | PAYMENT/TRANSFER OTHR S$ CHONG YEN MEI via PayNow: Fbj2004a insu | OTHR S$ | PAYMENT/TRANSFER","CHONG YEN MEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(205,"28/12/2020","SOON CHEN ONN | PAYMENT/TRANSFER OTHR S$ SOON CHEN ONN via PayNow: insurance premuim | OTHR S$ | PAYMENT/TRANSFER","SOON CHEN ONN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(298,"28/12/2020","NUR WANI BINTE MOHA | PAYMENT/TRANSFER OTHR NUR WANI BINTE MOHA Transfer FBN3736T | OTHR | PAYMENT/TRANSFER","NUR WANI BINTE MOHA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(37.45,"28/12/2020","MUHD HADI BIN HALI | PAYMENT/TRANSFER OTHR S$ MUHD HADI BIN HALI via PayNow: FBL193P JO | OTHR S$ | PAYMENT/TRANSFER","MUHD HADI BIN HALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(203,"28/12/2020","MUHAMMAD SHAH RIZAL | SI               FBP3909B IBG GIRO | OTHR | IBG GIRO","MUHAMMAD SHAH RIZAL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(409,"28/12/2020","AL HALIM BIN KAMARU | SI               FBL9348B IBG GIRO | OTHR | IBG GIRO","AL HALIM BIN KAMARU","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(260,"28/12/2020","NORIDAH BINTE OTHMA | SI               FBP1819R IBG GIRO | OTHR | IBG GIRO","NORIDAH BINTE OTHMA","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(285,"28/12/2020","MOHD HAZEEM BIN ABD | SI               FBM1617Y IBG GIRO | OTHR | IBG GIRO","MOHD HAZEEM BIN ABD","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(198,"28/12/2020","SALBIAH BTE ATAN | SI               FBM7103Z IBG GIRO | OTHR | IBG GIRO","SALBIAH BTE ATAN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(258,"28/12/2020","WONG VOON PIN | SI               FBQ6808J IBG GIRO | OTHR | IBG GIRO","WONG VOON PIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(209,"28/12/2020","RAJMOHAMED SHAHUL H | SI               FBQ8394Y IBG GIRO | OTHR | IBG GIRO","RAJMOHAMED SHAHUL H","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(518,"28/12/2020","MUHAMMAD QUSYAIRI B | SI                FN5115A IBG GIRO | OTHR | IBG GIRO","MUHAMMAD QUSYAIRI B","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(275,"28/12/2020","HAJJAH ROSE MELATI | SI               FBL5738K IBG GIRO | OTHR | IBG GIRO","HAJJAH ROSE MELATI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(437,"28/12/2020","WONG SIEW KEE | SI               FBM5844G IBG GIRO | OTHR | IBG GIRO","WONG SIEW KEE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(296,"28/12/2020","LUQMAANUL-HAKEEM BI | SI                FBL694K IBG GIRO | OTHR | IBG GIRO","LUQMAANUL-HAKEEM BI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(353,"28/12/2020","MUHAMMAD ASYRAF BIN | SI               FBQ2820Z IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ASYRAF BIN","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"28/12/2020","HASBULLAH BIN ABDUL | SI               FBL4649R IBG GIRO | OTHR | IBG GIRO","HASBULLAH BIN ABDUL","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(278,"28/12/2020","CHEW KUOK CHOON CLE | SI               FBL5211M IBG GIRO | OTHR | IBG GIRO","CHEW KUOK CHOON CLE","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(207,"28/12/2020","MUHAMMAD ROSLEE AFI | SI               FBN8386L IBG GIRO | OTHR | IBG GIRO","MUHAMMAD ROSLEE AFI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(276,"28/12/2020","MUHD SAHAFUDDIN B A | SI                FW4747K IBG GIRO | OTHR | IBG GIRO","MUHD SAHAFUDDIN B A","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-39.81,"28/12/2020","SP SERVICES LTD | GIRO Collection 8902370199 IBG GIRO | COLL 8902370199 | IBG GIRO","SP SERVICES LTD","COLL 8902370199","IBG GIRO"));
        bankRecords.add(new Transaction(-73.27,"28/12/2020","SP SERVICES LTD | GIRO Collection 8903495888 IBG GIRO | COLL 8903495888 | IBG GIRO","SP SERVICES LTD","COLL 8903495888","IBG GIRO"));
        bankRecords.add(new Transaction(-523.86,"28/12/2020","Keppel Electric Pte | CU00012269 IBG GIRO | COLL CU00012269 | IBG GIRO","Keppel Electric Pte","COLL CU00012269","IBG GIRO"));
        bankRecords.add(new Transaction(15,"28/12/2020","CHURCHILL MOSES LIV | PAYMENT/TRANSFER OTHR S$ CHURCHILL MOSES LIV via PayNow: FBN797L | OTHR S$ | PAYMENT/TRANSFER","CHURCHILL MOSES LIV","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250,"28/12/2020","IDROS BIN RAJAB ALI | PAYMENT/TRANSFER OTHR S$ IDROS BIN RAJAB ALI via PayNow: FBP8530B | OTHR S$ | PAYMENT/TRANSFER","IDROS BIN RAJAB ALI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(270,"28/12/2020","from NASRIN FATIMAH | OTHR - FBM8473Z FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NASRIN FATIMAH","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(-64.2,"28/12/2020","186457 | CHQ186457 CHEQUE |  | CHEQUE","186457","","CHEQUE"));
        bankRecords.add(new Transaction(-7000,"28/12/2020","186168 | CHQ186168 CHEQUE |  | CHEQUE","186168","","CHEQUE"));
        bankRecords.add(new Transaction(250,"29/12/2020","WONG WILLIAM | PAYMENT/TRANSFER OTHR S$ WONG WILLIAM via PayNow: william | OTHR S$ | PAYMENT/TRANSFER","WONG WILLIAM","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(500.95,"29/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 28/12/2020 | POS SETTLEMENT","ATAN MOTOR","28/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(-3855.42,"29/12/2020","P201219 | GIRO PAYMENT |  | GIRO PAYMENT","P201219","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(-0.2,"29/12/2020","P201219 | GIRO CHARGES |  | GIRO CHARGES","P201219","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-6414,"29/12/2020","CPF | BIZ GIRO | COLL 2624990 | GIRO","CPF","COLL 2624990","GIRO"));
        bankRecords.add(new Transaction(613,"29/12/2020","MOHAMMAD NASIR BIN | PAYMENT/TRANSFER OTHR S$ MOHAMMAD NASIR BIN via PayNow: Dec 2020 FBR2373S | OTHR S$ | PAYMENT/TRANSFER","MOHAMMAD NASIR BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(224,"29/12/2020","MUHAMAD YAZID B JUM | PAYMENT/TRANSFER OTHR MUHAMAD YAZID B JUM FBB290U | OTHR | PAYMENT/TRANSFER","MUHAMAD YAZID B JUM","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(360,"29/12/2020","MUHD ZAIDI BIN ABD | PAYMENT/TRANSFER OTHR MUHD ZAIDI BIN ABD FBL1111U | OTHR | PAYMENT/TRANSFER","MUHD ZAIDI BIN ABD","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(342,"29/12/2020","NUR ZUHAIRAH BINTE | PAYMENT/TRANSFER OTHR S$ NUR ZUHAIRAH BINTE via PayNow: FBM8318P | OTHR S$ | PAYMENT/TRANSFER","NUR ZUHAIRAH BINTE","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(307,"29/12/2020","MOHAMMAD YAZID BIN | PAYMENT/TRANSFER IHRP MOHAMMAD YAZID BIN FX8752M | IHRP | PAYMENT/TRANSFER","MOHAMMAD YAZID BIN","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(334,"29/12/2020","LIM CHENG SOON | PAYMENT/TRANSFER OTHR LIM CHENG SOON FBC4963E | OTHR | PAYMENT/TRANSFER","LIM CHENG SOON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(274,"29/12/2020","MOHAMMAD FARID BIN | PAYMENT/TRANSFER OTHR MOHAMMAD FARID BIN FBL8465C | OTHR | PAYMENT/TRANSFER","MOHAMMAD FARID BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(355,"29/12/2020","OW YONG HOA LEON | PAYMENT/TRANSFER OTHR OW YONG HOA LEON Transfer | OTHR | PAYMENT/TRANSFER","OW YONG HOA LEON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"29/12/2020","SIAH WOEI SHYANG | PAYMENT/TRANSFER OTHR S$ SIAH WOEI SHYANG via PayNow: FBM5529S | OTHR S$ | PAYMENT/TRANSFER","SIAH WOEI SHYANG","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(860,"29/12/2020"," | xx-4008 OCBC-103 YISHUN RING RD  S CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(340,"29/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(275,"30/12/2020","ZAREENAH BEEVI D/O | PAYMENT/TRANSFER OTHR S$ ZAREENAH BEEVI D/O via PayNow: FBM9353E | OTHR S$ | PAYMENT/TRANSFER","ZAREENAH BEEVI D/O","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(361,"30/12/2020","MUHAMMAD RAMADHAN B | PAYMENT/TRANSFER OTHR MUHAMMAD RAMADHAN B FBN396K Installment | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAMADHAN B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(214,"30/12/2020","ATAN MOTOR | 11146097500 POS SETTLEMENT | 29/12/2020 | POS SETTLEMENT","ATAN MOTOR","29/12/2020","POS SETTLEMENT"));
        bankRecords.add(new Transaction(203,"30/12/2020","from MUHAMMAD SHAHR | OTHR - Other FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MUHAMMAD SHAHR","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(355,"30/12/2020","SITI MAISARAH BINTE | PAYMENT/TRANSFER OTHR SITI MAISARAH BINTE FBJ4257Z | OTHR | PAYMENT/TRANSFER","SITI MAISARAH BINTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(282,"30/12/2020","from FBJ6173T ZALAN | LOAR - Fbj6173T FUND TRANSFER |  | FUND TRANSFER","from FBJ6173T ZALAN","","FUND TRANSFER"));
        bankRecords.add(new Transaction(251,"30/12/2020","from FBL3296G FARID | OTHR - Other FUND TRANSFER |  | FUND TRANSFER","from FBL3296G FARID","","FUND TRANSFER"));
        bankRecords.add(new Transaction(274,"30/12/2020","SITI NUR EISSABELLA | PAYMENT/TRANSFER OTHR SITI NUR EISSABELLA FBP726G | OTHR | PAYMENT/TRANSFER","SITI NUR EISSABELLA","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(204,"30/12/2020","NURUL KHAIRUNNISA B | PAYMENT/TRANSFER OTHR NURUL KHAIRUNNISA B fbp994z | OTHR | PAYMENT/TRANSFER","NURUL KHAIRUNNISA B","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(158,"30/12/2020","MUHAMMAD SALMAN BIN | PAYMENT/TRANSFER OTHR S$ MUHAMMAD SALMAN BIN via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","MUHAMMAD SALMAN BIN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(250.26,"30/12/2020","MOHAMMAD KHAIRUL AZ | PAYMENT/TRANSFER OTHR MOHAMMAD KHAIRUL AZ FBP6279R | OTHR | PAYMENT/TRANSFER","MOHAMMAD KHAIRUL AZ","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(230,"30/12/2020","MUHAMMAD KHAIRI BIN | PAYMENT/TRANSFER OTHR MUHAMMAD KHAIRI BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD KHAIRI BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(60,"30/12/2020","CHONG YEN MEI | PAYMENT/TRANSFER OTHR S$ CHONG YEN MEI via PayNow: Fbj2004a | OTHR S$ | PAYMENT/TRANSFER","CHONG YEN MEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(33,"30/12/2020","LTA | LTAFBA4051Y IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(2,"30/12/2020","LTA | LTAFBF1360Z IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(6,"30/12/2020","LTA | LTAFBA6110Y IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(10,"30/12/2020","LTA | LTAFBA2439B IBG GIRO | SUPP | IBG GIRO","LTA","SUPP","IBG GIRO"));
        bankRecords.add(new Transaction(300,"30/12/2020","MUHAMMAD FIRDAUS BI | SI                FBP898T IBG GIRO | OTHR | IBG GIRO","MUHAMMAD FIRDAUS BI","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(330,"30/12/2020","HUSAIN BIN MOHAMED | SI                FY9294G IBG GIRO | OTHR | IBG GIRO","HUSAIN BIN MOHAMED","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(316,"30/12/2020","ABDUL HAKIM BIN TAJ | SI               FBQ3011K IBG GIRO | OTHR | IBG GIRO","ABDUL HAKIM BIN TAJ","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(267,"30/12/2020","WAN CHEE SENG | SI               FBP3179R IBG GIRO | OTHR | IBG GIRO","WAN CHEE SENG","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(-59.35,"30/12/2020","Singapore Telecommu | 68472174 IBG GIRO | COLL 68472174 | IBG GIRO","Singapore Telecommu","COLL 68472174","IBG GIRO"));
        bankRecords.add(new Transaction(-429.51,"30/12/2020","Singapore Telecommu | 11538363 IBG GIRO | COLL 11538363 | IBG GIRO","Singapore Telecommu","COLL 11538363","IBG GIRO"));
        bankRecords.add(new Transaction(21.4,"30/12/2020","MOHAMAD FAIZ JUFRI | PAYMENT/TRANSFER OTHR S$ MOHAMAD FAIZ JUFRI via PayNow: 2T | OTHR S$ | PAYMENT/TRANSFER","MOHAMAD FAIZ JUFRI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(138,"30/12/2020"," | CASH DEPOSIT     CDM |  | CASH DEPOSIT     CDM","","","CASH DEPOSIT     CDM"));
        bankRecords.add(new Transaction(238,"30/12/2020","from NURUL AIN BINT | OTHR - Fbr9679P jan 2021 i FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from NURUL AIN BINT","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(1000,"30/12/2020","TAN JI WEI | PAYMENT/TRANSFER OTHR S$ TAN JI WEI via PayNow: fbq8228u | OTHR S$ | PAYMENT/TRANSFER","TAN JI WEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(217,"31/12/2020","NORJULIANAWATI BTE | PAYMENT/TRANSFER OTHR NORJULIANAWATI BTE FBQ2241U | OTHR | PAYMENT/TRANSFER","NORJULIANAWATI BTE","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(1000,"31/12/2020","TAN JI WEI | PAYMENT/TRANSFER OTHR S$ TAN JI WEI via PayNow: fbq8228u | OTHR S$ | PAYMENT/TRANSFER","TAN JI WEI","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(155,"31/12/2020","SEAH LI HOON | PAYMENT/TRANSFER OTHR SEAH LI HOON FBM3627E | OTHR | PAYMENT/TRANSFER","SEAH LI HOON","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-25,"31/12/2020","27/12/2020 | xx-8008 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","27/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-25,"31/12/2020","27/12/2020 | xx-8008 LTA E-SERVICE VRLS     SIN DEBIT PURCHASE |  | DEBIT PURCHASE","27/12/2020","","DEBIT PURCHASE"));
        bankRecords.add(new Transaction(-0.2,"31/12/2020","P201221 | GIRO CHARGES |  | GIRO CHARGES","P201221","","GIRO CHARGES"));
        bankRecords.add(new Transaction(-6741.01,"31/12/2020","P201221 | GIRO PAYMENT |  | GIRO PAYMENT","P201221","","GIRO PAYMENT"));
        bankRecords.add(new Transaction(271,"31/12/2020","MUHAMMAD MAHMUD B M | PAYMENT/TRANSFER OTHR MUHAMMAD MAHMUD B M FBG1921A | OTHR | PAYMENT/TRANSFER","MUHAMMAD MAHMUD B M","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(165,"31/12/2020","MOHAMED AIDIL BIN A | PAYMENT/TRANSFER OTHR S$ MOHAMED AIDIL BIN A via PayNow: Fbh6749t | OTHR S$ | PAYMENT/TRANSFER","MOHAMED AIDIL BIN A","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-3496.04,"31/12/2020","P201220 | FUND TRANSFER Fund Transfer to account 652325689001 SGD 3 496.04@1 ATAN MOTORING - Nov 2020 | CT0020478821 | FUND TRANSFER","P201220","CT0020478821","FUND TRANSFER"));
        bankRecords.add(new Transaction(300,"31/12/2020","MARIANI BINTE ABDUL | PAYMENT/TRANSFER OTHR S$ MARIANI BINTE ABDUL via PayNow: FBR8570A | OTHR S$ | PAYMENT/TRANSFER","MARIANI BINTE ABDUL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(15,"31/12/2020","SARAVANAN NADARAJA | PAYMENT/TRANSFER OTHR S$ SARAVANAN NADARAJA via PayNow: FBM9426D | OTHR S$ | PAYMENT/TRANSFER","SARAVANAN NADARAJA","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(10400,"31/12/2020","KUAH PENG YAN | PAYMENT/TRANSFER OTHR S$ KUAH PENG YAN via PayNow: 198903552W | OTHR S$ | PAYMENT/TRANSFER","KUAH PENG YAN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(350,"31/12/2020","LEONG KAM SOONG | PAYMENT/TRANSFER OTHR LEONG KAM SOONG Transfer | OTHR | PAYMENT/TRANSFER","LEONG KAM SOONG","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(213,"31/12/2020","MUHAMMAD IRFAN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD IRFAN BIN Transfer | OTHR | PAYMENT/TRANSFER","MUHAMMAD IRFAN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(800,"31/12/2020","LEE SENG CHUN | PAYMENT/TRANSFER OTHR S$ LEE SENG CHUN via PayNow: Fbq8228u | OTHR S$ | PAYMENT/TRANSFER","LEE SENG CHUN","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(170,"31/12/2020","SAZALAN BIN ABDUL H | SI               FBH6208M IBG GIRO | OTHR | IBG GIRO","SAZALAN BIN ABDUL H","OTHR","IBG GIRO"));
        bankRecords.add(new Transaction(341,"31/12/2020","HAH KIM CHUEN | PAYMENT/TRANSFER OTHR HAH KIM CHUEN Transfer | OTHR | PAYMENT/TRANSFER","HAH KIM CHUEN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(438,"31/12/2020","ARIEF SUFIAN BIN ZU | PAYMENT/TRANSFER IHRP ARIEF SUFIAN BIN ZU Instalment of Ducati FBF5439E | IHRP | PAYMENT/TRANSFER","ARIEF SUFIAN BIN ZU","IHRP","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(284,"31/12/2020","from MOHAMAD SHAHRI | OTHR - Fbm 6464 m FUND TRANSFER | via PayNow-UEN | FUND TRANSFER","from MOHAMAD SHAHRI","via PayNow-UEN","FUND TRANSFER"));
        bankRecords.add(new Transaction(500,"31/12/2020","MUHAMMAD RAQIB BIN | PAYMENT/TRANSFER OTHR MUHAMMAD RAQIB BIN fbp1371p | OTHR | PAYMENT/TRANSFER","MUHAMMAD RAQIB BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(215,"31/12/2020","M I MUHAMMAD ABDULL | PAYMENT/TRANSFER OTHR S$ M I MUHAMMAD ABDULL via PayNow: FBK4960X | OTHR S$ | PAYMENT/TRANSFER","M I MUHAMMAD ABDULL","OTHR S$","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(315,"31/12/2020","MUHAMMAD SAHRIN BIN | PAYMENT/TRANSFER OTHR MUHAMMAD SAHRIN BIN Transfer fbm 646 u | OTHR | PAYMENT/TRANSFER","MUHAMMAD SAHRIN BIN","OTHR","PAYMENT/TRANSFER"));
        bankRecords.add(new Transaction(-1054.1,"31/12/2020","186456 | CHQ186456 CHEQUE |  | CHEQUE","186456","","CHEQUE"));
        bankRecords.add(new Transaction(-1458.75,"31/12/2020","186459 | CHQ186459 CHEQUE |  | CHEQUE","186459","","CHEQUE"));
        bankRecords.add(new Transaction(-17.25,"31/12/2020"," | TRANS CHARGE |  | TRANS CHARGE","","","TRANS CHARGE"));
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
