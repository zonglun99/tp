package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class ShopResetCommand extends Command {
    /**
     * Constructor for ShopResetCommand.
     */
    public ShopResetCommand() {
    }

    /**
     * Executes the "shopreset" command.
     *
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    @Override
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList)
            throws FridgetException {
        if (shoppingList.getShoppingList("r").isEmpty()) {
            throw new FridgetException("You currently have nothing in your shopping list.");
        }

        if (ui.getResetReconfirm(Ui.CommandType.SHOPRESET)) {
            shoppingList.resetList();
            ui.printShopResetMessage();
        }
    }
}
