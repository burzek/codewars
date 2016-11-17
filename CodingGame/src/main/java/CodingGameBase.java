import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author boris.brinza
 */
public abstract class CodingGameBase {
	private String inputFile;

	public CodingGameBase(String inputFile) {
		this.inputFile = inputFile;
	}

	public final void run() {
		String data = readFile();
		doTask(data);
	}

	private String readFile() {
		try {
			Path p = Paths.get(getClass().getResource(inputFile).toURI());
			return Files.readAllLines(p).stream().collect(Collectors.joining("\n"));
		} catch (Exception e) {
			throw new RuntimeException("Cannot read file " + inputFile);
		}
	}

	protected abstract void doTask(String input);

}
