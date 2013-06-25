package com.ongroa.cocktails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cocktail implements Serializable {

	private static final long serialVersionUID = -583890345982417049L;
	public static final String NAME = "name";
	public static final String ALAPSZESZ = "alapszesz";
	public static final String POHAR = "pohar";
	public static final String DISZ = "disz";
	public static final String FAJTA = "fajta";
	public static final String OSSZETEVOK = "osszetevok";

	private String name;
	private String alapszesz;
	private String pohar;
	private String diszites;
	private String fajta;
	private List<Osszetevo> osszetevok;

	public Cocktail() {
		osszetevok = new ArrayList<Osszetevo>();
	}

	public Cocktail(String n, String a, String p, String d, String f) {
		this();
		this.name = n;
		this.alapszesz = a;
		this.pohar = p;
		this.diszites = d;
		this.fajta = f;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlapszesz() {
		return alapszesz;
	}

	public void setAlapszesz(String alapszesz) {
		this.alapszesz = alapszesz;
	}

	public String getPohar() {
		return pohar;
	}

	public void setPohar(String pohar) {
		this.pohar = pohar;
	}

	public String getDiszites() {
		return diszites;
	}

	public void setDiszites(String diszites) {
		this.diszites = diszites;
	}

	public String getFajta() {
		return fajta;
	}

	public void setFajta(String fajta) {
		this.fajta = fajta;
	}

	public List<Osszetevo> getOsszetevok() {
		return osszetevok;
	}

	public void setOsszetevok(List<Osszetevo> osszetevok) {
		this.osszetevok = osszetevok;
	}

	public void addOsszetevo(Osszetevo o) {
		String name = o.getNev();
		if (! getNevek().contains(name)) {
			osszetevok.add(o);
		}
	}

	public Osszetevo getOsszetevo(String name) {
		for (int i = 0; i < osszetevok.size(); i++) {
			if (osszetevok.get(i).getNev().equals(name)) {
				return osszetevok.get(i);
			}
		}
		return null;
	}

	public List<String> getOsszetevokAsString() {
		List<String> ret = new ArrayList<String>();
		for (Osszetevo o : osszetevok) {
			String item = String.format("%s %s", 
					o.getMennyiseg(), o.getNev());
			ret.add(item);
		}
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = String.format(
				"%s \nAlapszesz: %s \nPohár: %s \nFajta: %s\n",
				name, alapszesz, pohar, fajta);
		ret += "Összetevők:\n";
		for (Osszetevo o : osszetevok) {
			ret += o.toString();
		}
		return ret;
	}

	public void evaluateOsszetevok(Cocktail refCocktail) {
		for (Osszetevo o : osszetevok) {
			String quizOsszetevo = String.format("%s %s", 
					o.getMennyiseg(), o.getNev());
			if (refCocktail.getOsszetevokAsString().contains(quizOsszetevo)) {
				o.setValid(true);
			}
		}
	}
	
	private boolean isOsszetevokEquals(List<Osszetevo> o1, 
			List<Osszetevo> o2) {
		String item = null;
		Osszetevo o = null;
		if (o1 == null) return false;
		if (o2 == null) return false;
		if (o1.size() != o2.size()) return false;
		Set<String> s1 = new HashSet<String>();
		Set<String> s2 = new HashSet<String>();
		for (int i = 0; i < o1.size(); i++) {
			o = o1.get(i);
			item = String.format("%s %s", 
					o.getMennyiseg(), o.getNev());
			s1.add(item);
			o = o2.get(i);
			item = String.format("%s %s", 
					o.getMennyiseg(), o.getNev());
			s2.add(item);
		}
		return s1.equals(s2);
	}
	
	private List<String> getNevek() {
		List<String> ret = new ArrayList<String>();
		for (Osszetevo o : osszetevok) {
			ret.add(o.getNev());
		}
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (! (obj instanceof Cocktail)) return false;
		Cocktail o = (Cocktail)obj;
		return o.alapszesz.equals(this.alapszesz) &&
				o.pohar.equals(this.pohar) &&
//				o.diszites.equals(this.diszites) &&
				o.fajta.equals(this.fajta) &&
				isOsszetevokEquals(o.getOsszetevok(), this.getOsszetevok());
	}

	public List<Osszetevo> getOsszetevokSorted() {
		List<Osszetevo> ret = new ArrayList<Osszetevo>(osszetevok);
		Collections.sort(ret);
		return ret;
	}
}
