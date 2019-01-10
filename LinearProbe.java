package probingsequence;

public class LinearProbe implements ProbingSequence {
	public int getP(String key, int i) {
		return i;
	}
}