package us.lsi.pd.numerodearboles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Preconditions;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class NumeroDeArboles implements ProblemaPD<Integer, Integer> {

	public static NumeroDeArboles create(Integer n) {
		return new NumeroDeArboles(n, 1,0);
	}
	
	public static NumeroDeArboles create(Integer n, Integer m, Integer t) {
		return new NumeroDeArboles(n, m, t);
	}

	private Integer n; //Numero de v�rtices
	private Integer m; //Tama�o de la lista de �rboles
	private Integer t; //Nivel
	public static Integer nmh;  //N�mero m�ximo de hijos	

	private NumeroDeArboles(Integer n, Integer m, Integer t) {
		super();
		this.n = n;
		this.m = m;
		this.t = t;
	}

	@Override
	public ProblemaPD.Tipo getTipo() {
		return Tipo.Otro;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean esCasoBase() {
		return this.n==0 || this.m==0;
	}

	@Override
	public Sp<Integer> getSolucionCasoBase() {
		Sp<Integer> r = null;
		if (n==0) {
			r = Sp.create(null, 1.);
		}else if(m==0){			
			r = null;
		}
		return r;
	}

	@Override
	public Sp<Integer> seleccionaAlternativa(List<Sp<Integer>> ls) {
		Double s= ls.stream().mapToDouble(x->x.propiedad).sum();
		return Sp.create(null,s);
	}

	@Override
	public ProblemaPD<Integer, Integer> getSubProblema(Integer a, int np) {
		NumeroDeArboles r;
		if(np==0) {
			if (a==0) {
				r = NumeroDeArboles.create(0,1,t);
			} else{
				r = NumeroDeArboles.create(a-1,NumeroDeArboles.nmh,t+1);
			}
		} else 
			r = NumeroDeArboles.create(n-a, m-1, t);
		return r;
	}

	@Override
	public Sp<Integer> combinaSolucionesParciales(Integer a, List<Sp<Integer>> ls) {
		return Sp.create(a, ls.get(0).propiedad*ls.get(1).propiedad);
	}

	@Override
	public List<Integer> getAlternativas() {
		Preconditions.checkArgument(this.n>=0);
		return IntStream.rangeClosed(0,this.n).boxed().collect(Collectors.toList());
	}

	@Override
	public int getNumeroSubProblemas(Integer a) {
		return 2;
	}

	@Override
	public Integer getSolucionReconstruida(Sp<Integer> sp) {
		return null;
	}

	@Override
	public Integer getSolucionReconstruida(Sp<Integer> sp, List<Integer> ls) {
		return null;
	}

	@Override
	public Double getObjetivoEstimado(Integer a) {
		return Double.MIN_VALUE;
	}

	@Override
	public Double getObjetivo() {
		return Double.MAX_VALUE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NumeroDeArboles))
			return false;
		NumeroDeArboles other = (NumeroDeArboles) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[n=" + n + ", m=" + m + "]";
	}

	
}
