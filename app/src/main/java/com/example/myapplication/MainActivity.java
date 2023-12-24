import java.util.*;
class MSEB
{
private long CNumber;
private String Cname,CAddress,Conntype,BDate;
private int Prevreading,Curreading,Totalunit;
private double Fixrate,Eleccharge,Carrycharge,Netamt;
public void accept()
{
Scanner s=new Scanner(System.in);
System.out.println("Enter Customer Number");
CNumber=s.nextLong();
s.nextLine();
System.out.println("Enter Customer Name");
Cname=s.nextLine();
System.out.println("Enter Customer Address");
CAddress=s.nextLine();
System.out.println("Enter Customer Connection Type");
Conntype=s.nextLine();
System.out.println("Enter Customer Bill Date");
BDate=s.nextLine();
System.out.println("Enter Customer Previous reading");
Prevreading=s.nextInt();
System.out.println("Enter Customer Current Reading");
Curreading=s.nextInt();
Totalunit=Curreading-Prevreading;
}
public void calculate()
{
if(Conntype.equalsIgnoreCase("Domestic"))
Fixrate=110;
else
Fixrate=310;
if(Totalunit<=100)
Eleccharge=Totalunit*3.46;
else
if(Totalunit<=300)
Eleccharge=(Totalunit-100)*7.43+346;
else
if(Totalunit<=500)
Eleccharge=(Totalunit-300)*10.32+(200*7.43)+346;
else
if(Totalunit<=1000)
Page 26 of 28
Eleccharge=(Totalunit-500)*11.71+(300*10.32)+(200*7.43)+346;
else
Eleccharge=(Totalunit-1000)*12.0+(500*11.71)+(300*10.32)+(200*7.43)+346;
Carrycharge=1.45*Totalunit;
Netamt=Math.round(Fixrate+Eleccharge+Carrycharge);
}
public void display()
{
System.out.println("\t \t \t \t MSEB Dhule Dept");
System.out.println("------------------------------------------------------------------------------
-----------------------------");
System.out.println("Consumer Number: \t"+CNumber);
System.out.println("Consumer Name: \t "+Cname);
System.out.println("Consumer Address: \t "+CAddress);
System.out.println("Consumer Connection Type: \t "+Conntype);
System.out.println("Consumer Bill Date: \t "+BDate);
System.out.println("------------------------------------------------------------------------------
-----------------------------");
System.out.println("Previous Reading: \t"+Prevreading+"\t Current Reading: 
\t"+Curreading+"\t Total Units: \t"+Totalunit);
System.out.println("------------------------------------------------------------------------------
-----------------------------");
System.out.println("Fix Charge: \t"+Fixrate);
System.out.println("Electric Charge: \t"+Eleccharge);
System.out.println("Net amt: \t"+Netamt);
System.out.println("------------------------------------------------------------------------------
-----------------------------");
String x=Double.toString(Netamt);
String y="";
for(int i=0;i<x.length();i++)
{
char ch=x.charAt(i);
switch(ch)
{
case '0':y=y+"Zero"+" ";break;
case '1':y=y+"One"+" ";break;
case '2':y=y+"Two"+" ";break;
case '3':y=y+"Three"+" ";break;
case '4':y=y+"Four"+" ";break;
case '5':y=y+"Five"+" ";break;
case '6':y=y+"Six"+" ";break;
case '7':y=y+"Seven"+" ";break;
case '8':y=y+"Eight"+" ";break;
case '9':y=y+"Nine"+" ";break;
case '.':y+=".";
}
}
System.out.println("In Words "+y);
System.out.println("------------------------------------------------------------------------------
-----------------------------");
}
public static void main(String[] args)
{
MSEB p=new MSEB();
p.accept();
p.calculate();
p.display();
}
}