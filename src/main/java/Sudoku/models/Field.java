package Sudoku.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Field {
    private int value;

    public Field() {
        this.value = 0;
    }

    /*
     * Gets the value of Field.
     */
    public int getValue() {
        return this.value;
    }

    /*
     * Sets the value of Field.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /*
     * Checks whether the object is equal to this
     * @Param object The object that wants to get checked with the current field.
     */
    public boolean equal(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (!(object instanceof Field)) {
            return false;
        }

        Field field = (Field) object;
        return new EqualsBuilder().append(field.getValue(), this.value).isEquals();
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}