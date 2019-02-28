package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;

public interface OptimizationRule {
    Token[] optimize(Token[] srcToken);
}
