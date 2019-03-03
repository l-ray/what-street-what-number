package de.lray.addressline;

import de.lray.addressline.tokenizer.rule.OptimizationRule;
import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Splits a given input string into token/units.
 *
 * Part of the tokenization are
 * - a removal of stop words (not adding any value to the information) as e..g empty strings,
 * comma
 * - cleaning from unnecessary white-spaces
 * - an optimization of tokens based on optimization rules given as parameters during the class instantiation.
 *
 * Seen from an ETL process, this step represents an "extraction" phase.
 */
final class Tokenizer {

    private static final List<String> _STOPWORDS = Arrays.asList(",","");
    private static final Predicate<String> _REMOVE_STOPWORDS = item -> !_STOPWORDS.contains(item);

    private static final String _WHITESPACES_AS_REGEX = "[,;]";
    private static final Function<String, String> _CLEAN_FROM_WHITESPACES = item -> item.replaceFirst(_WHITESPACES_AS_REGEX, "");
    private static final Function<String, Token> _STRING_TO_TOKEN = item -> TokenFactory.asToken(item);

    private final OptimizationRule[] _optimizer;

    Tokenizer(OptimizationRule[] optimizer) {
        this._optimizer = optimizer;
    }

    Token[] tokenize(String addressString) {
        if (addressString == null) {
            throw new IllegalArgumentException("input string can not be null.");
        }
        String[] splitString = addressString.split(" ");
        Token[] token = Arrays
                .stream(splitString)
                .filter(_REMOVE_STOPWORDS)
                .map(_CLEAN_FROM_WHITESPACES)
                .map(_STRING_TO_TOKEN)
                .toArray(Token[]::new);
        return optimizeToken(token);
    }

    private Token[] optimizeToken(Token[] srcToken) {
        for (OptimizationRule anOptimizer : _optimizer) {
            srcToken = anOptimizer.optimize(srcToken);
        }
        return srcToken;
    }
}
