import javafx.scene.control.Button;

public class Card {
    private String color;
    private int number;

    public Card(String color, int number) {
        this.color = color;
        this.number = number;
    }

    public Button getButton() {
        Button button = new Button();
        button.setText(toString());
        return button;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public  String toString() {
        return color + " " + number;
    }
}
