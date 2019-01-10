package probingsequence;

public class QuadraticProbe implements ProbingSequence {
	public int getP(String key, int i) {
		Double d = Math.pow(-1, i-1);
		Double dd = Math.pow(((i+1)/2), 2);
		int neg = d.intValue();
		int pos = dd.intValue();
		return neg*pos;
	}
}