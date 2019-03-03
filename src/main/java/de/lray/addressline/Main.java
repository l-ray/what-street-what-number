package de.lray.addressline;

import java.io.PrintStream;

public class Main {

    static PrintStream out = System.out;

    public static void main(String[] args) {
        validate(args);
        String addressAsString = args[0];
        String addressAsSerializedJSON = convert(addressAsString);
        out.println(addressAsSerializedJSON);
    }

    private static void validate(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one parameter needed.");
        }
    }

    static String convert(String addressAsString) {
        AddressLineConverter processor = AddressLineConverterFactory.createDefault();
        return processor.asJSON(addressAsString).toString();
    }
}
