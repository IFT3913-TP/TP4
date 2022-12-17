package ua.karatnyk;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.OfflineJsonWorker;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestCurrencyConvertor {
    private final CurrencyConversion conversion = new OfflineJsonWorker().parser();
    private final String[] currencyList = {"USD", "CAD", "GBP", "EUR", "CHF", "INR", "AUD"};


    @Test
    public void testCurrencyTestBothInDomain() {

        try {
            CurrencyConvertor.convert(0, "USD", "CAD", this.conversion);
        } catch (ParseException e) {
            fail(e);
        }
    }

    @Test
    public void testCurrencyTestOneInDomain() {
        ParseException thrown = Assertions.assertThrows(ParseException.class, () -> {
            double result = CurrencyConvertor.convert(0, "USD", "ZZZ", this.conversion);
        }, "ParseException error was expected");
    }

    @Test
    public void testCurrencyTestNoneInDomain() {
        ParseException thrown = Assertions.assertThrows(ParseException.class, () -> {
            double result = CurrencyConvertor.convert(0, "XXX", "ZZZ", this.conversion);
        }, "ParseException error was expected");
    }

    @Test
    public void testCurrencyTestOneInJson() {
        ParseException thrown = Assertions.assertThrows(ParseException.class, () -> {
            double result = CurrencyConvertor.convert(0, "NGN", "ZZZ", this.conversion);
        }, "ParseException error was expected");
    }

    @Test
    public void testCurrencyTestBothInJson() {
        ParseException thrown = Assertions.assertThrows(ParseException.class, () -> {
            double result = CurrencyConvertor.convert(0, "NGN", "ALL", this.conversion);
        }, "ParseException error was expected");
    }

    @Test
    public void testCurrencyTestDomainAndJson() {
        ParseException thrown = Assertions.assertThrows(ParseException.class, () -> {
            double result = CurrencyConvertor.convert(0, "USD", "NGN", this.conversion);
        }, "ParseException error was expected");
    }

    @Test
    public void testAmountInDomain() throws ParseException {
        double rate = conversion.getRates().get("CAD") / conversion.getRates().get("USD");
        double amount = 1000;
        double result = CurrencyConvertor.convert(amount, "USD", "CAD", this.conversion);
        assertEquals(rate * amount, result, 0);
    }

    @Test
    public void testAmountFrontierLow() throws ParseException {

        double rate = conversion.getRates().get("CAD") / conversion.getRates().get("USD");
        double amount = 0;
        double result = CurrencyConvertor.convert(amount, "USD", "CAD", this.conversion);
        assertEquals(rate * amount, result, 0);

    }

    @Test
    public void testAmountFrontierUp() throws ParseException {
        double rate = conversion.getRates().get("CAD") / conversion.getRates().get("USD");
        double amount = 10000;
        double result = CurrencyConvertor.convert(amount, "USD", "CAD", this.conversion);
        assertEquals(rate * amount, result, 0);
    }

    @Test
    public void testAmountExtremeLow() {
        double amount = -1000;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double result = CurrencyConvertor.convert(amount, "USD", "CAD", this.conversion);
        }, "IllegalArgumentException error was expected");
    }

    @Test
    public void testAmountExtremeUp() {
        double amount = 11000;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double result = CurrencyConvertor.convert(amount, "USD", "CAD", this.conversion);
        }, "IllegalArgumentException error was expected");
    }

}
