package br.com.xpto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.entity.Util.CidadeEntradaCSV;
import br.com.xpto.entity.Util.CidadesDistancia;
import br.com.xpto.entity.Util.EstadoQtd;
import br.com.xpto.entity.Util.EstadoQtdCidade;
import br.com.xpto.model.Cidades;
import br.com.xpto.model.Estado;
import br.com.xpto.service.CidadeService;
import br.com.xpto.service.EstadoService;
import br.com.xpto.util.DistanciaEntrePontos;
import br.com.xpto.util.EntradaCVS;

@RestController
@RequestMapping(value = "/cidades")
public class CidadesApi {

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private EstadoService estadoService;

	@RequestMapping(method = RequestMethod.GET, value = "/carregar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> carregaCsv() {
		List<Cidades> cidadesSalvas = null;
		try {
			List<CidadeEntradaCSV> ceCsv = EntradaCVS.carregarCVS();

			for (CidadeEntradaCSV c : ceCsv) {
				Estado estado = estadoService.findEstado(c.getUf());
				if (estado != null) {
					Cidades cidades = new Cidades(c.getIbge(), c.getNome(), c.isCapital(), c.getLon(), c.getLat(),
							c.getNoAccents(), c.getNamesAlternatives(), c.getMicroregion(), c.getMensoregion(), estado);
					cidadeService.salvarCidades(cidades);
				} else {
					Estado estadoCriado = new Estado(c.getUf());
					Cidades cidades = new Cidades(c.getIbge(), c.getNome(), c.isCapital(), c.getLon(), c.getLat(),
							c.getNoAccents(), c.getNamesAlternatives(), c.getMicroregion(), c.getMensoregion(),
							estadoCriado);
					cidadeService.salvarCidades(cidades);
				}
			}
			cidadesSalvas = cidadeService.all();

			if (cidadesSalvas.size() < 1) {
				return new ResponseEntity<>(cidadesSalvas, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cidadesSalvas, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cidades-capitais", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaCidadesCapitais() {

		List<Cidades> cc = cidadeService.cidadesCapitais();

		return new ResponseEntity<>(cc, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/estado-mais-menos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaCidadesMaiorMenorEstado() {
		EstadoQtd estadoQtd = null;
		try {
			int maior = 0, menor = 1000;
			String estadoMaior = null, estadoMenor = null;

			List<Estado> estados = estadoService.all();
			for (Estado e : estados) {
				int retorno = cidadeService.buscarPorEstados(e);
				if (retorno > maior) {
					maior = retorno;
					estadoMaior = e.getUf();
				}
				if (retorno < menor) {
					menor = retorno;
					estadoMenor = e.getUf();
				}
			}
			estadoQtd = new EstadoQtd(estadoMaior, estadoMenor, maior, menor);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(estadoQtd, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/estado-com-qtd-cidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaEstadosQtdCidade() {

		List<EstadoQtdCidade> listEstadoQtdCidade = new ArrayList<>();
		try {
			List<Estado> estados = estadoService.all();
			for (Estado e : estados) {
				int retorno = cidadeService.buscarPorEstados(e);
				EstadoQtdCidade eqc = new EstadoQtdCidade(e.getUf(), retorno);
				listEstadoQtdCidade.add(eqc);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listEstadoQtdCidade, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cidade-pelo-ibge", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaCidadePeloIbge(@RequestParam(name = "ibge", required = false) String ibge) {
		Cidades cidade = null;
		try {
			cidade = cidadeService.buscarPeloIbge(ibge);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cidades-por-estado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaNomeCidadesPorEstado(
			@RequestParam(name = "estado", required = false) String estado) {
		List<Cidades> cidade = null;
		try {
			cidade = cidadeService.buscarCidadePeloEstado(estado.toUpperCase());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveCidades(@RequestBody Cidades cidade) {
		Cidades c = null;
		try {
			c = cidadeService.salvarCidadesComRetorno(cidade);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		try {
			cidadeService.delet(cidadeService.find(id));

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/qtd-total", produces = MediaType.APPLICATION_JSON_VALUE)
	public int retornaQtd() {
		int qtd = cidadeService.quantidadeDeRegistrosTotal();
		return qtd;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cidade-mais-distantes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retornaCidadeMaisDistantes() {
		CidadesDistancia cd = null;
		double distancia = 0;
		String ca = null, cb = null;
		try {
			List<Cidades> cidades = cidadeService.all();
			for (Cidades c : cidades) {
				for (Cidades cc : cidades) {
					double d = DistanciaEntrePontos.distancia(Double.parseDouble(c.getLat()),
							Double.parseDouble(c.getLon()), Double.parseDouble(cc.getLat()),
							Double.parseDouble(cc.getLon()), "K");
					if (d > distancia) {
						distancia = d;
						ca = c.getNome();
						cb = cc.getNome();
					}
				}
			}
			cd = new CidadesDistancia(ca, cb, distancia);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cd, HttpStatus.OK);
	}

}
