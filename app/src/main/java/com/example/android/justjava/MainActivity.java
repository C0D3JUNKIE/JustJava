package com.example.android.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int price = 0;
    private static int numberOfCoffees = 0;
    private static final int pricePerCup = 5;

    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(numberOfCoffees);
        String priceMessage = "Total number of coffees ordered is: " + numberOfCoffees;
        priceMessage = priceMessage + "\nThank You.";
        displayMessage(priceMessage);
        calculatePrice(numberOfCoffees);
        displayPrice(price);
        createOrderSummary();

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * this method displays the price
     * @param number
     */
    private void displayPrice(int number){
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message){
        TextView displayMessage = findViewById(R.id.message_display);
        displayMessage.setText(message);
    }

    /**
     * Increment the quantity
     *
     * @param view
     */
    public void incrementQuantity(View view){
        numberOfCoffees++;
        display(numberOfCoffees);
    }

    /**
     * Decrement the quantity
     *
     * @param view
     */
    public void decrementQuantity(View view){
        if(numberOfCoffees == 0){

        }else{
            numberOfCoffees--;
            display(numberOfCoffees);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity){
        price = quantity * pricePerCup;
        return price;
    }

    /**
     * returns customer name
     *
     * @return String customerMessage returning Customer Name
     */
    private void createOrderSummary(){
        String currentTime = DateFormat.getDateTimeInstance().format(now);
        String customerName = "Name:  Sir Loin";
        TextView displayMessage = findViewById(R.id.customer_name);
        displayMessage.setText(customerName);
        TextView todaysDate = findViewById(R.id.todays_date);
        todaysDate.setText(currentTime);
    }


}