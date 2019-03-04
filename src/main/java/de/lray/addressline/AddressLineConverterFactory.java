package de.lray.addressline;

import de.lray.addressline.parser.MultipleNumbersParser;
import de.lray.addressline.parser.NumberFirstParser;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.parser.SimpleAddressLineParser;
import de.lray.addressline.tokenizer.rule.HouseNumberKeywordRule;
import de.lray.addressline.tokenizer.rule.HouseNumberStickyRule;
import de.lray.addressline.tokenizer.rule.OptimizationRule;

/**
 * Factory class constructing converter, holding all dependencies.
 *
 * For different regions, different configurations can lead to enhanced performance - as the
 * order of parser can be aligned with the distribution of address formats.
 */
public class AddressLineConverterFactory {

    private AddressLineConverterFactory(){
        // static class, nothing to be initiated.
    }

    public static AddressLineConverter createDefault() {
        return new AddressLineConverter(
            new Tokenizer(
                    new OptimizationRule[]{
                            new HouseNumberStickyRule(),
                            new HouseNumberKeywordRule(new String[]{"no"})
                    }
            ), new Mapper(
                    new Parser[]{
                        new SimpleAddressLineParser(),
                        new NumberFirstParser(),
                        new MultipleNumbersParser()
                    }
            )
        );
    }
}
