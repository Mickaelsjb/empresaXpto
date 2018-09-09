package br.com.xpto.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import br.com.xpto.entity.Util.CidadeEntradaCSV;

public class EntradaCVS {

	public static List<String> leArquivo(String arquivo) throws FileNotFoundException, IOException {
		List<String> list = new ArrayList<>();

		File file = new File(arquivo);
		if (!file.exists()) {
			return null;
		}
		BufferedReader br = new BufferedReader(new FileReader(arquivo));
		String linha;

		while ((linha = br.readLine()) != null) {
			list.add(linha);
		}
		br.close();
		return list;
	}

	public static List<CidadeEntradaCSV> carregarCVS() {
		List<CidadeEntradaCSV> ceCvs = new ArrayList<>();
		try {
			List<String> listaString = leArquivo("arquivos/Cidades.csv");
			listaString.remove(0);
			for (String s : listaString) {
				String a[] = new String[10];
				a = s.split(",");

				CidadeEntradaCSV cidadeEntradaCSV = new CidadeEntradaCSV(a[0], a[1], a[2],
						verificaTrue(a[3]), a[4], a[5], a[6], a[7], a[8], a[9]);
				ceCvs.add(cidadeEntradaCSV);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ceCvs;
	}
	
	public static boolean verificaTrue(String vf) {
		if (vf.equals("true")) {
			return true;
		}
		else {
			return false;
		}
	}
}
