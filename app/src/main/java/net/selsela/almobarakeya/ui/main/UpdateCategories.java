package net.selsela.almobarakeya.ui.main;

public class UpdateCategories {
    int selectedPos;

    public UpdateCategories(int i) {
        this.selectedPos = i;
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    @Override
    public String toString() {
        return "UpdateCategories{" +
                "selectedPos=" + selectedPos +
                '}';
    }
}
