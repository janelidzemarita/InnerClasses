package Figure;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.ElementIterator;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		/*
		 * Input File Paths D:\\TBC_Academy\\Generics\\src\\InputFiles\\Circles_in.dat
		 * D:\\TBC_Academy\\Generics\\src\\InputFiles\\Rectangles_in.dat
		 * D:\\TBC_Academy\\Generics\\src\\InputFiles\\Triangles_in.dat
		 */

//		/* uncomment to test

		TreeSet<Circle> circleTreeset = CreateTreesets.circlesTreeSet(args[0]);
		TreeSet<Rectangle> rectangleTreeset = CreateTreesets.rectanglesTreeSet(args[1]);
		TreeSet<Triangle> triangleleTreeset = CreateTreesets.trianglesTreeSet(args[2]);

		ArrayList<Circle> circleArrayList = new ArrayList<>(CreateArrayList.circlesArrayList(args[0]));
		ArrayList<Rectangle> rectangleArrayList = new ArrayList<>(CreateArrayList.rectanglesArrayList(args[1]));
		ArrayList<Triangle> triangleleArrayList = new ArrayList<>(CreateArrayList.trianglesArrayList(args[2]));

		
//		 */
		

		
		getMinMax(circleArrayList, rectangleArrayList, triangleleArrayList);
		
		/*
		TreeSet<Figure> treeSets = new TreeSet<>();
		treeSets.addAll(CreateTreesets.circlesTreeSet(args[0]));
		treeSets.addAll(CreateTreesets.rectanglesTreeSet(args[1]));
		treeSets.addAll(CreateTreesets.trianglesTreeSet(args[2]));

		ArrayList<Figure> arrayLists = new ArrayList<>();
		arrayLists.addAll(CreateArrayList.circlesArrayList(args[0]));
		arrayLists.addAll(CreateArrayList.rectanglesArrayList(args[1]));
		arrayLists.addAll(CreateArrayList.trianglesArrayList(args[2]));
		*/
		
//
//		getMinMax(treeSets);
//		System.out.println(getMinMax(treeSets));
	}

	public static ArrayList<Figure> getMinMax(ArrayList<Circle> circleArrayList, 
			ArrayList<Rectangle> rectangleArrayList, ArrayList<Triangle> triangleleArrayList ) {

		ArrayList<Figure> result = new ArrayList<>();
		
		try {
			
			Collections.sort(circleArrayList);
			Collections.sort(rectangleArrayList);
			Collections.sort(triangleleArrayList);
			
			result.add(circleArrayList.get(0));
			result.add(circleArrayList.get(circleArrayList.size()-1));
			
			result.add(rectangleArrayList.get(0));
			result.add(rectangleArrayList.get(rectangleArrayList.size()-1));
			
			result.add(triangleleArrayList.get(triangleleArrayList.size()-1));
			result.add(triangleleArrayList.get(0));
			
		}catch (Exception e) {
			System.err.println("Some Figure is missing!");
		}
		
		return  result;
		
	}

	/*
	 * Version for min and max for all figures at once. All figure types (C, R, T)
	 * are in TreeSet<Figure> if there is only one or two of the types, for the
	 * missing treeset the method will thrwo an exception with the error message
	 */

	public static TreeSet<Figure> getMinMax(TreeSet<Figure> figureTreeset) {
		TreeSet<Figure> result = new TreeSet<>();
		try {
			TreeSet<Circle> circleTreeset = new TreeSet<>();
			TreeSet<Rectangle> rectangleTreeset = new TreeSet<>();
			TreeSet<Triangle> triangleleTreeset = new TreeSet<>();
			for (Figure element : figureTreeset) {
				if (element instanceof Circle) {
					circleTreeset.add((Circle) element);
				} else if (element instanceof Triangle) {
					triangleleTreeset.add((Triangle) element);
				} else
					rectangleTreeset.add((Rectangle) element);
			}

			result.add(circleTreeset.last());
			result.add(circleTreeset.first());
			result.add(rectangleTreeset.last());
			result.add(rectangleTreeset.first());
			result.add(triangleleTreeset.first());
			result.add(triangleleTreeset.last());
			// prints the max rectangle, triangle and circle as requested in ex 2.
			System.out.println("Max Circle: Circle {radius=" + circleTreeset.last().getRadius() + "}"
					+ " Max Rectangle: " + rectangleTreeset.last() + " Max Triangle: " + triangleleTreeset.first());
			// returns a treeset of figures with min and max triangle, circle and rectangle

		} catch (Exception e) {
			System.err.println("Some Figure is missing!");
		}
		return result;
	}

	// MinMax second version
	public static Figure getMinMax(TreeSet<Circle> circleTreeset, TreeSet<Rectangle> rectangleTreeset,
			TreeSet<Triangle> triangleleTreeset) {

		TreeSet<Figure> result = new TreeSet<>();

		try {
			result.add(circleTreeset.last());
			result.add(circleTreeset.first());
			result.add(rectangleTreeset.last());
			result.add(rectangleTreeset.first());
			result.add(triangleleTreeset.first());
			result.add(triangleleTreeset.last());
		} catch (Exception e) {
			System.err.println("Some Figure is missing!");
		}
		return result.last();
	}

}

class CreateTreesets {

	public static TreeSet<Circle> circlesTreeSet(String args) throws FileNotFoundException, IOException {
		Pattern formatCircle = Pattern.compile("([0-9]+)");

		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Circles_out_unique.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			TreeSet<Circle> setCircle = new TreeSet<Circle>();
			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatCircle.matcher(str);
				if (match.matches()) {
					setCircle.add(new Circle("Circle", Double.parseDouble(str)));
				}
			}
			/*
			 * Iterator<Circle> circles = setCircle.iterator(); while (circles.hasNext()) {
			 * bfw.write(Double.toString(circles.next().getRadius()) + "\n");
			 * 
			 * }
			 */
			return setCircle;
		}
	}

	public static TreeSet<Rectangle> rectanglesTreeSet(String args) throws FileNotFoundException, IOException {
		Pattern formatRectangle = Pattern.compile("([0-9]+[-][0-9]+)");
		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Rectangles_out.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			TreeSet<Rectangle> setRectangle = new TreeSet<Rectangle>();
			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatRectangle.matcher(str);
				if (match.matches()) {
					String[] lst = str.split("-");
					setRectangle.add(new Rectangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1])));

				}
			}
			/*
			 * for(Rectangle rec : setRectangle){ bfw.write(rec.getLength() + "-" +
			 * rec.getHigth() + "\n"); System.out.println(rec.getPerimeter()); }
			 */
			return setRectangle;
		}

	}

	public static TreeSet<Triangle> trianglesTreeSet(String args) throws FileNotFoundException, IOException {
		Pattern formatTriangle = Pattern.compile("([0-9]+[-][0-9]+[-][0-9]+)");
		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Triangles_out.dat.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			TreeSet<Triangle> setTriangle = new TreeSet<Triangle>();

			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatTriangle.matcher(str);
				if (match.matches()) {
					String[] lst = str.split("-");
					setTriangle.add(new Triangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1]),
							Double.parseDouble(lst[2])));
				}
			}
			/*
			 * for(Triangle triangle : setTriangle){ bfw.write(triangle.getAB() + "-" +
			 * triangle.getBC() + "-" + triangle.getCA() + "\n"); }
			 */
			return setTriangle;
		}
	}

}

class CreateArrayList {

	public static ArrayList<Circle> circlesArrayList(String args) throws FileNotFoundException, IOException {
		Pattern formatCircle = Pattern.compile("([0-9]+)");

		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Circles_out_unique.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			ArrayList<Circle> setCircle = new ArrayList<>();
			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatCircle.matcher(str);
				if (match.matches()) {
					setCircle.add(new Circle("Circle", Double.parseDouble(str)));
				}
			}
			/*
			 * Iterator<Circle> circles = setCircle.iterator(); while (circles.hasNext()) {
			 * bfw.write(Double.toString(circles.next().getRadius()) + "\n");
			 * 
			 * }
			 */
			return setCircle;
		}
	}

	public static ArrayList<Rectangle> rectanglesArrayList(String args) throws FileNotFoundException, IOException {
		Pattern formatRectangle = Pattern.compile("([0-9]+[-][0-9]+)");
		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Rectangles_out.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			ArrayList<Rectangle> setRectangle = new ArrayList<Rectangle>();
			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatRectangle.matcher(str);
				if (match.matches()) {
					String[] lst = str.split("-");
					setRectangle.add(new Rectangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1])));

				}
			}
			/*
			 * for(Rectangle rec : setRectangle){ bfw.write(rec.getLength() + "-" +
			 * rec.getHigth() + "\n"); System.out.println(rec.getPerimeter()); }
			 */
			return setRectangle;
		}

	}

	public static ArrayList<Triangle> trianglesArrayList(String args) throws FileNotFoundException, IOException {
		Pattern formatTriangle = Pattern.compile("([0-9]+[-][0-9]+[-][0-9]+)");
		try (FileInputStream inStrm = new FileInputStream(args);
				BufferedInputStream bf = new BufferedInputStream(inStrm);
				BufferedReader read = new BufferedReader(new InputStreamReader(bf, "UTF-8"));
				FileOutputStream outStrm = new FileOutputStream(
						"D:\\TBC_Academy\\Generics\\src\\OutputFiles\\Triangles_out.dat.dat");
				OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
				BufferedWriter bfw = new BufferedWriter(bfo);) {
			ArrayList<Triangle> setTriangle = new ArrayList<Triangle>();

			while (read.ready()) {
				String str = read.readLine();
				Matcher match = formatTriangle.matcher(str);
				if (match.matches()) {
					String[] lst = str.split("-");
					setTriangle.add(new Triangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1]),
							Double.parseDouble(lst[2])));
				}
			}
			/*
			 * for(Triangle triangle : setTriangle){ bfw.write(triangle.getAB() + "-" +
			 * triangle.getBC() + "-" + triangle.getCA() + "\n"); }
			 */
			return setTriangle;
		}
	}

}
