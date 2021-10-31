package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.ingredient.Ingredient;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> shoppingList;

    /**
     * Constructor for ShoppingList.
     */
    public ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    /**
     * Adds an ingredient into shoppingList.
     *
     * @param ingredient The ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient, int quantity) {
        assert  ingredient != null : "Ingredient must not be null!";
        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                ingredient1.addQuantity(quantity);
                return;
            }
        }

        shoppingList.add(ingredient);
    }

    /**
     * Removes an ingredient from the shopping list.
     *
     * @param ingredient Ingredient to be removed.
     * @param quantity Quantity to be removed.
     */
    public void removeIngredient(Ingredient ingredient, int quantity) {
        assert ingredient != null : "Ingredient must not be null!";
        assert quantity != 0 : "Quantity removed must no be 0!";

        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                if (quantity >= ingredient1.getQuantity()) {
                    shoppingList.remove(ingredient1);
                    return;
                }
                ingredient1.removeQuantity(quantity);
            }
        }
    }

    /**
     * Checks if an item with the same name exists in the shopping list and returns its quantity.
     *
     * @param ingredient Ingredient to be searched.
     * @return Quantity of ingredient in the shoppingList if exist.
     */
    public int searchIngredientNameExist(Ingredient ingredient) {
        for (Ingredient ingredient1 : shoppingList) {
            if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                return ingredient1.getQuantity();
            }
        }
        return 0;
    }

    /**
     * Sorts the ingredient in shoppingList by description.
     *
     * @return sorted ingredient ArrayList.
     * @throws FridgetException Error thrown when there are no items in the shoppingList.
     */
    private ArrayList<Ingredient> sortIngredient() throws FridgetException {
        if (shoppingList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your shopping list.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Ingredient> sortedList = new ArrayList<>(shoppingList);
        sortedList.sort(Ingredient.IngNameComparator);
        return sortedList;
    }

    /**
     * Returns the current shopping list.
     *
     * @return List of ingredients in the shopping list.
     */
    public ArrayList<Ingredient> getShoppingList(String sortType) throws FridgetException {
        assert sortType != null : "Sort type must not be null!";
        switch (sortType.toLowerCase()) {
        case "r":
            return shoppingList;
        case "default":
            return sortIngredient();
        default:
            throw new FridgetException("Unrecognisable shoplist command. Try: <shoplist>");
        }
    }

    /**
     * Resets the shopping list.
     */
    public void resetList() {
        shoppingList = new ArrayList<>();
    }
}
