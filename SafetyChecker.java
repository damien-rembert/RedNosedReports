import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SafetyChecker {

	public static void main(String[] args) {
		List<String> file = loadFile("input.txt");
		// List<String> file = loadFile("testInput.txt");
		int safeOnes = 0;
		// List<Integer> input = file.stream().map(Integer::parseInt).toList();
		for (String line : file) {
			// List<Integer> levels = new ArrayList<>();
			String[] stringLevel = line.split(" ");
			try {
				List<Integer> levels = new ArrayList<>();
				for (String s : stringLevel) {
					levels.add(Integer.parseInt(s));
				}
				if (isSafe(levels)) {
					System.out.println("safe");
					safeOnes++;
				}
			} catch (Exception e) {
				System.out.println("unsafe");
			}

		}

		System.out.println("Safe ones: " + safeOnes);

	}

	static boolean isSafe(List<Integer> levels) {

		boolean isIncreasing = false;
		for (int i = 0; i < levels.size() - 1; i++) {
			int a = levels.get(i);
			int b = levels.get(i + 1);
			// for (int i = 0; i < levels.size() - 1; i++) {
			System.out.println(i);
			if (a == b) {
				System.out.println("no change");
				return false;
			}
			if (!isWellSpaced(a, b)) {
				System.out.println("not well spaced");
				return false;
			}
			if (i == 0) {
				isIncreasing = isIncreasing(a, b);
			} else if (isIncreasing != isIncreasing(a, b)) {
				System.out.println("not consistent increasing");

				return false;
			}
		}

		return true;

	}

	static boolean isIncreasing(int a, int b) {
		return a < b;
	}

	static boolean isWellSpaced(int a, int b) {
		int diff = Math.abs(a - b);
		return diff >= 1 && diff <= 3;
	}

	static List<String> loadFile(String filename) {
		List<String> read = new ArrayList<>();

		try {
			Path path = Paths.get(filename);
			read = Files.readAllLines(path);
		} catch (Exception e) {
			System.out.println(e);
		}
		return read;
	}

}