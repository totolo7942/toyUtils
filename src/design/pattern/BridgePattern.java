package design.pattern;

// Implementor interface
interface Color {
    void applyColor();
}

// Concrete Implementor 1
class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying red color");
    }
}

// Concrete Implementor 2
class BlueColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying blue color");
    }
}

// Abstraction class
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void applyColor();
}

// Refined Abstraction 1
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.print("Circle: ");
        color.applyColor();
    }
}

// Refined Abstraction 2
class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.print("Square: ");
        color.applyColor();
    }
}

// 테스트 코드
public class BridgePattern {
    public static void main(String[] args) {
        Color red = new RedColor();
        Color blue = new BlueColor();

        Shape circle = new Circle(red);
        circle.applyColor();

        Shape square = new Square(blue);
        square.applyColor();
    }
}