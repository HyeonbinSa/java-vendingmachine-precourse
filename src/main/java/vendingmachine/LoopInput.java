package vendingmachine;

import camp.nextstep.edu.missionutils.Console;

public class LoopInput {
    private static InputMessage inputMessage = new InputMessage();
    private static OutputMessage outputMessage = new OutputMessage();

    public void inputMethod() {
    }

    public void input() {
        while (true) {
            try {
                inputMethod();
                break;
            } catch (IllegalArgumentException exception) {
                outputMessage.printErrorMessage(exception.getMessage());
            }
        }
    }

    public String inputString(String message) {
        inputMessage.printInputMessage(message);
        String inputData = Console.readLine();
        return inputData;
    }
}
