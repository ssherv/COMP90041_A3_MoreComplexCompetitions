# COMP90041_A3_MoreComplexCompetitions
An extension of the program SimpleCompetitions that was developed in Assignment 2. SimpleCompetitions helps companies (e.g., retailers) create competitions to boost their sales.
<br>
<h4> Completed in June 2021 (Semester 1) </h4>
<hr>

<b>Functionality:</b>
A retailer can use this software to create a lucky draw game. In the game, a customer is given entries equivalent to the size of its purchase after their single-purchase final balance, after discounts, reach a specifc amount, say $50. Once the competition time ends, the retailer draws winners and the winning customers will get points added to their membership account; customers can use these points for future purchases if they wish. 

<b>Topics of focus:</b>
Class design and implementation, control flow structures, basic I/O, arrays, inheritance and polymorphism, exception handling, and file I/O.
<hr>

See 'A3_Specification' for the competition policy and application walk-through including intended inputs and expected outputs. This application supports two different modes: (1) LuckyNumbers competition (similar to SimpleCompetitions from A2) and (2) RandomPick competition. This application includes error handling as it does not assume that data (e.g. unser inputs, file context) will be of the correct type/format.

To run this console application in NORMAL MODE:
<ol>
  <li> Download the Java code files in the "A3" folder </li>
  <li> Open a console and navigate to the project directoty (where the .java classes reside) </li>
  <li> Run and complile the program with: <code> javac *.java </code> </li>
  <li> Run the command: <code> java SimpleCompetitions </code> </li>
</ol>

To run this console application in TESTING MODE:
<ol>
  <li> Download the Java code files in the "A3" folder </li>
  <li> Download the test files (including input and output) in the "A3-test-cases" folder </li>
  <li> Download the CSV files (including bills.csv and members.csv) in the "A3-CSV" folder </li>
  <li> Place all files in the same directory as your project directory (where the .java classes reside) </li>
  <li> Open a console and navigate to the project directory </li>
  <li> Run and complile the program with: <code> javac *.java </code> </li>
  <li> Run the command: <code> java SimpleCompetitions < input1.txt > my-output1.txt </code> <br> 
  (This will run SimpleCompetitions using contents in "input1.txt" as input and write the output to "my-output1.txt". </li>
  <li> Inpect the file "my-output1.txt" with the provided output files (e.g.output1.txt) to test that the program is working as intended </li>
  <li> Repeat steps 6 and 7 to test all given input and output pairs. <br>
  <b>NOTE </b> that test cases 6, 7 and 8 must be run in order. Moreover, test cases 1 to 6 should be run with the original bill file (i.e., bills.csv) </li>
</ol>
