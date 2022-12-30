package core.basesyntax.service.impl;

import core.basesyntax.service.ProcessService;
import java.util.Map;

public class ProcessServiceImpl implements ProcessService {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int FRUIT_TYPE_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;
    private static final String COMMA_DELIMITTER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String BALANCE_CONSTANT = "b";
    private static final String SUPPLY_CONSTANT = "s";
    private static final String RETURN_CONSTANT = "r";
    private static final String PURCHASE_CONSTANT = "p";

    @Override
    public StringBuilder getQuantity(String lines, Map<String, Integer> fruitMap) {
        StringBuilder reportBuilder = new StringBuilder("fruit,quantity\n");
        String[] splittedLines = lines.split(NEW_LINE_DELIMITER);
        for (int i = 1; i < splittedLines.length; i++) {
            String[] splittedLine = splittedLines[i].trim().split(COMMA_DELIMITTER);
            switch (splittedLine[OPERATION_TYPE_INDEX]) {
                case BALANCE_CONSTANT:
                    int quantity = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                    fruitMap.put(splittedLine[FRUIT_TYPE_INDEX], quantity);
                    break;
                case SUPPLY_CONSTANT:
                case RETURN_CONSTANT:
                    quantity = fruitMap.get(splittedLine[FRUIT_TYPE_INDEX])
                            + Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                    fruitMap.replace(splittedLine[FRUIT_TYPE_INDEX], quantity);
                    break;
                case PURCHASE_CONSTANT:
                    quantity = fruitMap.get(splittedLine[FRUIT_TYPE_INDEX])
                            - Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                    fruitMap.replace(splittedLine[FRUIT_TYPE_INDEX], quantity);
                    break;
                default:
                    break;
            }
        }
        return reportBuilder;
    }
}