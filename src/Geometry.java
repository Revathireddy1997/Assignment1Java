import java.util.Scanner;
//Creating a class to represent a point
class GeometryPoint {
    private double x;
    private double y;
//constructor to initialize the point with x&y coordinates
    public GeometryPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
//get method to get x coordinates
    public double getX()
    {
        return x;
    }
//get method to get y coordinate
    public double getY()
    {
        return y;
    }
//Calculate the distance between this point and the another point
    public double calculateDistanceTo(GeometryPoint other) {
        double deltaX = x - other.getX();
        double deltaY = y - other.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
//class to represent triangle
class GeometryTriangle
{
    private GeometryPoint[] vertices;
    //constructor to create triangle from 3 points
    public GeometryTriangle(GeometryPoint point1, GeometryPoint point2, GeometryPoint point3) {
        vertices = new GeometryPoint[3];
        vertices[0] = point1;
        vertices[1] = point2;
        vertices[2] = point3;
    }
//calculating the perimeter of the triangle
    public double calculatePerimeter()
    {
        double totalPerimeter = 0;
        for (int sideIndex = 0; sideIndex < 3; sideIndex++) {
            int nextSideIndex = (sideIndex + 1) % 3;
            totalPerimeter += vertices[sideIndex].calculateDistanceTo(vertices[nextSideIndex]);
        }
        return totalPerimeter;
    }
//checking if the given triangle is isosceles
    public boolean checkIsosceles()
    {
        double s1 = vertices[0].calculateDistanceTo(vertices[1]);
        double s2 = vertices[1].calculateDistanceTo(vertices[2]);
        double s3 = vertices[2].calculateDistanceTo(vertices[0]);
        return s1 == s2 || s1 == s3 || s2 == s3;
    }
//check if the given point is inside the triangle
    public boolean containsPoint(GeometryPoint p)
    {
        double totalarea = 0;
        for (int i = 0; i < 3; i++)
        {
            int nextIndex = (i + 1) % 3;
            totalarea += 0.5 * Math.abs(vertices[i].getX() * (vertices[nextIndex].getY() - p.getY()) +
                    vertices[nextIndex].getX() * (p.getY() - vertices[i].getY()) +
                    p.getX() * (vertices[i].getY() - vertices[nextIndex].getY()));
        }
        double triangleArea = 0.5 * (vertices[0].getX() * (vertices[1].getY() - vertices[2].getY()) +
                vertices[1].getX() * (vertices[2].getY() - vertices[0].getY()) +
                vertices[2].getX() * (vertices[0].getY() - vertices[1].getY()));
//If the absolute difference is less than a small value (1e-6),
// the point is considered inside the triangle.
        return Math.abs(totalarea - triangleArea) < 1e-6;
    }
}
public class Geometry
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//User has to give the number of count of the triangles
        System.out.print("Enter the number of count of the triangles: ");
        int numTriangles = scanner.nextInt();
        GeometryTriangle[] triangles = new GeometryTriangle[numTriangles];
//This for loop is to gather the vertices to create the triangles
        for (int triangleIndex = 0; triangleIndex < numTriangles; triangleIndex++) {
            System.out.println("Triangle " + (triangleIndex + 1) + " vertices:");
            System.out.print("Enter x-coordinate for vertex 1: ");
            double vertex1X = scanner.nextDouble();
            System.out.print("Enter y-coordinate for vertex 1: ");
            double vertex1Y = scanner.nextDouble();
            System.out.print("Enter x-coordinate for vertex 2: ");
            double vertex2X = scanner.nextDouble();
            System.out.print("Enter y-coordinate for vertex 2: ");
            double vertex2Y = scanner.nextDouble();
            System.out.print("Enter x-coordinate for vertex 3: ");
            double vertex3X = scanner.nextDouble();
            System.out.print("Enter y-coordinate for vertex 3: ");
            double vertex3Y = scanner.nextDouble();
            GeometryPoint p1 = new GeometryPoint(vertex1X, vertex1Y);
            GeometryPoint p2 = new GeometryPoint(vertex2X, vertex2Y);
            GeometryPoint p3 = new GeometryPoint(vertex3X, vertex3Y);
            triangles[triangleIndex] = new GeometryTriangle(p1, p2, p3);
        }
//checking if the triangle is isosceles and calculate the perimeter
        for (int triangleIndex = 0; triangleIndex < numTriangles; triangleIndex++) {
            System.out.println("Triangle " + (triangleIndex + 1) + " Perimeter: " + triangles[triangleIndex].calculatePerimeter());
            if (triangles[triangleIndex].checkIsosceles()) {
                System.out.println("Triangle " + (triangleIndex + 1) + " is isosceles");
            } else {
                System.out.println("Triangle " + (triangleIndex + 1) + " is not isosceles");
            }
        }
//check if a point is inside each triangle
        System.out.println("Enter point to check if is inside triangle:");
        System.out.print("Enter x: ");
        double px = scanner.nextDouble();
        System.out.print("Enter y: ");
        double py = scanner.nextDouble();
        GeometryPoint pointToCheck = new GeometryPoint(px, py);
        for (int i = 0; i < numTriangles; i++) {
            if (triangles[i].containsPoint(pointToCheck)) {
                System.out.println("The point is inside Triangle " + (i + 1));
            } else {
                System.out.println("The point is not inside Triangle " + (i + 1));
            }
        }
    }
}
