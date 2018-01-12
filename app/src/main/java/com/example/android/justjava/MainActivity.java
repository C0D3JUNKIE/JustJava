package com.example.android.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String mCustomerName;
    private String[] mCustomerEmail;
    private String mOrderSummary;


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
        displayPrice(price);
        calculatePrice(numberOfCoffees);
        String priceMessage = "Total number of coffees ordered is: " + numberOfCoffees;
        priceMessage = priceMessage + "\nThank You.";
        displayMessage(priceMessage);
        createOrderSummary();
        composeEmail(mCustomerEmail, mCustomerName);
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
        if(numberOfCoffees == 100){
            Toast.makeText(this, "100 Coffees is the limit!", Toast.LENGTH_SHORT).show();
        }else{
            numberOfCoffees++;
            display(numberOfCoffees);
        }
    }

    /**
     * Decrement the quantity
     *
     * @param view
     */
    public void decrementQuantity(View view){
        if(numberOfCoffees == 0){
            Toast.makeText(this, "If you going to order make sure you have at least one item!", Toast.LENGTH_LONG).show();
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

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        if((hasChocolate == true) && (hasWhippedCream == true)){
            price = quantity * (pricePerCup + 3);
            return price;
        }else if((hasChocolate == true) && (hasWhippedCream == false)){
            price = quantity * (pricePerCup + 2);
            return price;
        }else if((hasChocolate == false) && (hasWhippedCream == true)){
            price = quantity * (pricePerCup + 1);
            return price;
        }else{
            price = quantity * pricePerCup;
            return price;
        }
    }

    /**
     * Creates summary of the current order and displays it.
     *
     */
    private void createOrderSummary(){

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        String currentTime = DateFormat.getDateTimeInstance().format(now);

        EditText customerNameTextField = (EditText) findViewById(R.id.edit_customer_name);
        mCustomerName = customerNameTextField.getText().toString();

        TextView displayMessage = findViewById(R.id.customer_name);
        displayMessage.setText(mCustomerName);

        TextView todaysDate = findViewById(R.id.todays_date);
        todaysDate.setText(currentTime);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        TextView toppingsMessage = (TextView) findViewById(R.id.toppings_message);
        toppingsMessage.setText("Has Whipped Cream:  " + hasWhippedCream + "\nChocolate Sprinkles:  " + hasChocolate);

        mOrderSummary = "Thank you " + mCustomerName + " for your order." +
        "\n" + "\nYou ordered: " + numberOfCoffees + " coffees." + "\nYour total cost was:  $" + price;

    }




    //MOVE TO EXPERIMENTAL BRANCH OR CONSIDER JUST SCRAPPING - This might be CRAP!!!!
//    private String addWhippedCream(){
//        boolean whippedCream = false;
//        String toWhipOrNotToWhip = "Whipped cream has been added to your coffee.";
//        String notToWhip = "No whipped cream added.";
//        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
//        if(checkBox.isChecked(){
//            whippedCream = true;
//            Log.v("MainActivity","************ LOG *** Checkbox is checked! Checkbox is checked! Checkbox is checked! *** LOG ************");
//            return toWhipOrNotToWhip;
//        }else{
//            whippedCream = false;
//            Log.v("MainActivity","************ LOG *** Checkbox is NOT checked! Checkbox is NOT checked! Checkbox is NOT checked! *** LOG ************");
//            return notToWhip;
//        }
//    }

    /**
     * @param addresses
     * @param subject
     */
    public void composeEmail(String[] addresses, String subject) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, mCustomerEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, "C0d3Junkies Just Java:  Receipt for " + mCustomerName + "'s coffee order!");
        intent.putExtra(Intent.EXTRA_TEXT, mOrderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


}