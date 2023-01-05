package core.basesyntax.strategy.impl;

import core.basesyntax.strategy.CountStrategy;

public class PurchaseCountStrategyImpl implements CountStrategy {
    @Override
    public int count(int currentAmount, int operationAmount) {
        return currentAmount - operationAmount;
    }
}