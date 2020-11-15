package Sudoku.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Field {
    private int value;
    private boolean isEditable = false;

    public Field() {
        this.value = 0;
    }

    /**
     * Gets the value of Field.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value of Field.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets whether the field is editable or not.
     */
    public boolean getIsEditable() {
        return this.isEditable;
    }

    /**
     * Sets whether the field is editable or not.
     */
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * Checks whether the object is equal to this
     * @Param object The object that wants to get checked with the current field.
     */
    @Override
    public boolean equals(Object object) {
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