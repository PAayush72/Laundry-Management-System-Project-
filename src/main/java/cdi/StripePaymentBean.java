package com.example.jsf;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class StripePaymentBean {

    private static final String STRIPE_SECRET_KEY = "sk_test_51QYT1DENCrEGvxq4KAsYOO7c01FWCtQm5GCOh5tauowRn8Bo4S99lgriB4SBJEznsAAvmfnfIB9Cr6A4DhSi5IBt00lk0ZMCsV";
    private double amount;
    private String clientSecret;

    static {
        Stripe.apiKey = STRIPE_SECRET_KEY; // Initialize Stripe API key
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter for clientSecret
    public String getClientSecret() {
        return clientSecret;
    }

    // Method to create PaymentIntent and generate clientSecret
    public void createPaymentIntent() throws StripeException {
        // Convert the amount to cents
        long amountInPaisa = (long) (amount * 100);

        // Set up the PaymentIntent creation parameters
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInPaisa)  // Amount in cents (Stripe uses the smallest currency unit)
                .setCurrency("inr")        // Currency in USD (can change based on your needs)
                .addPaymentMethodType("upi")  // Accept card payments
                .build();

        // Create the PaymentIntent using Stripe API
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Store the client secret for front-end usage
        this.clientSecret = paymentIntent.getClientSecret();
    }
}
