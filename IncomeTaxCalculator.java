/*
Logic Overview:
Given a person's filing status and income, output the amount of tax they owe using marginal tax rates.
*/

import java.util.*;

public class IncomeTaxCalculator {

	public static void main(String []args) {

    // Create a scanner to user input for their filing status and income
    Scanner scan = new Scanner(System.in);

    // Prompt the user response for their filing status
    System.out.println("Welcome to your income tax calculator.\nPlease enter your filing status:");
    System.out.println("Single (Enter 0)");
    System.out.println("Married Filing Jointly / Widow (Enter 1)");
    System.out.println("Married Filing Separately (Enter 2)");
    System.out.println("Head of Household (Enter 3)");
    int filingStatus = scan.nextInt(); 
    // Clean user input for invalid entries
    if(filingStatus < 0 || filingStatus > 3) {
        System.out.println("Invalid entry, defaulting to Single");
        filingStatus = 0;
    }

    // Prompt the user response for their income
    System.out.println("Please enter your income");
    int income = scan.nextInt(); 
    // Clean user input for invalid entries
    if(income < 0) {
        System.out.println("Invalid entry, defaulting to 0");
        income = 0;
    }

    // Store the tax rate level upper bounds in a 2d array (status and bracket) to reference below in loops
    double[][] taxBrackets = {{8350, 33950, 82250, 171550, 372950, Integer.MAX_VALUE}, // single rates
                              {16700, 67900, 137050, 208850, 372950, Integer.MAX_VALUE}, // married joint
                              {8350, 33950, 68525, 104425, 186475, Integer.MAX_VALUE}, // married separate
                              {11950, 45500, 117450, 190200, 372950, Integer.MAX_VALUE}}; // head of household
    // Store the tax rates in an array
    double[] taxBracketRates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};

    // Find the total marginal tax rate
    double[] bracketTaxableIncome = new double[6];
    double incomeLeft = income;
    double totalTax = 0;
    for(int i=0; i < bracketTaxableIncome.length; i++) {
        // First, find the user's taxable income for each bracket
        double currentBracket = taxBrackets[filingStatus][i];
        if(incomeLeft - currentBracket >= 0) {
           bracketTaxableIncome[i] = currentBracket; // The full bracket is filled
          } else if(incomeLeft == 0) {
           bracketTaxableIncome[i] = 0; // No income for the bracket
          } else {
           bracketTaxableIncome[i] = incomeLeft; // Part of the bracket is filled
          }
        incomeLeft = incomeLeft - bracketTaxableIncome[i];
        // Find the user's tax owed for each bracket, then add it to the total total
        double currentTax = bracketTaxableIncome[i] * taxBracketRates[i];
        // Add this bracket's tax to the total tax due
        totalTax = totalTax + currentTax;
    }

    // Print results
    System.out.println("Filing status: " + filingStatus);
    System.out.println("Income: " + income);
    System.out.println("Total Tax Due: " + totalTax);

  } 
}
