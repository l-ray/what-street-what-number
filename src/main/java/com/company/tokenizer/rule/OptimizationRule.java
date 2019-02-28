package com.company.tokenizer.rule;

import com.company.tokenizer.token.Token;

public interface OptimizationRule {
    Token[] optimize(Token[] srcToken);
}
