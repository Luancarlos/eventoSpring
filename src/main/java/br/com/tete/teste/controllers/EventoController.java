package br.com.tete.teste.controllers;

import br.com.tete.teste.models.Convidado;
import br.com.tete.teste.models.Evento;
import br.com.tete.teste.repository.ConvidadoRepository;
import br.com.tete.teste.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cr;

    @RequestMapping(value="/cadastrarEvento",method = RequestMethod.GET)
    public String cadastrar(){
        return "evento/cadastrarEvento";
    }

    @RequestMapping(value="/cadastrarEvento",method = RequestMethod.POST)
    public String cadastrar(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("erroinput","Preencha todos os campos!");
        }
        else{
            er.save(evento);
            attributes.addFlashAttribute("sucessoinput","Evento adicionado com sucesso !");
        }

        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/listaEvento")
    public ModelAndView lista(){
        ModelAndView md = new ModelAndView("evento/ListaEvento");
        Iterable<Evento> eventos = er.findAll();
        md.addObject("eventos",eventos);
        return md;
    }

    @RequestMapping("/deletarEvento/{codigo}")
    public String excluirEvento(@PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/listaEvento";
    }
    @RequestMapping("/deletarConvidado/{cpf}")
    public String excluirConvidado(@PathVariable("cpf") String cpf){
        Convidado convidado = cr.findByCpf(cpf);
        cr.delete(convidado);
        Evento evento = convidado.getEvento();
        return "redirect:/detalhesEvento/"+evento.getCodigo();
    }



    @RequestMapping(value="/detalhesEvento/{codigo}",method = RequestMethod.GET)
    public ModelAndView detalheEvento(@PathVariable("codigo") long codigo){
        // criando variavel do tipo evento e adicionando os dados a ela
        Evento evento = er.findByCodigo(codigo);
        // criando um modelo e setando qual pagina ira redenrizar
        ModelAndView md = new ModelAndView("evento/detalhesEvento");
        // passando dados como atributos
        md.addObject("evento",evento);

        // buscar dados convidado
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        md.addObject("convidados",convidados);


        return md;
    }

    @RequestMapping(value="/detalhesEvento/{codigo}",method = RequestMethod.POST)
    public String detalheEventoForm(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result , RedirectAttributes attributes){
        // se houver erro, compo vazio
        if(result.hasErrors()){
            attributes.addFlashAttribute("erroinput","Erro, Preencha todos os campos!");
        }
        else{

            Evento evento = er.findByCodigo(codigo);
            // adicionando a class o codigo do evento
            convidado.setEvento(evento);
            cr.save(convidado);
            attributes.addFlashAttribute("sucessoinput","Convidado adicionado com sucesso !");
        }
        return "redirect:/detalhesEvento/{codigo}";


    }


}
