package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.StorageService;
import core.basesyntax.strategy.OperationCalculator;
import core.basesyntax.strategy.OperationCalculatorStrategy;
import java.util.List;
import java.util.Map;

public class StorageServiceImpl implements StorageService {
    private final OperationCalculatorStrategy operationStrategy;
    private final Storage storage;

    public StorageServiceImpl(OperationCalculatorStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
        storage = new Storage();
    }

    @Override
    public Map<String, Integer> update(List<FruitTransaction> fruitTransactions) {
        Map<String, Integer> fruitMap = storage.getFruitMap();
        for (FruitTransaction transaction : fruitTransactions) {
            FruitTransaction.Operation operation = transaction.getOperation();
            OperationCalculator countStrategy = operationStrategy.getCountStrategy(operation);
            String fruitName = transaction.getFruit();
            int operationQuantity = transaction.getQuantity();
            int currentQuantity =
                    operation == FruitTransaction.Operation.BALANCE ? 0 : fruitMap.get(fruitName);
            int newAmount = countStrategy.count(currentQuantity, operationQuantity);
            fruitMap.put(fruitName, newAmount);
        }
        return fruitMap;
    }
}
