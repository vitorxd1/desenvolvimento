/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Senac
 */
public class ModTarefas {
    private String tarefas, descricao;
    private int id;
    private LocalTime horaInicio, horaFim;
    private LocalDate dataTarefa;
    private String status;
    
    public String getTarefas(){
        return tarefas;
    }
    
    public void setTarefas(String tarefas){
        this.tarefas = tarefas;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the horaInicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFim
     */
    public LocalTime getHoraFim() {
        return horaFim;
    }

    /**
     * @param horaFim the horaFim to set
     */
    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    

    /**
     * @return the dataTarefa
     */
    public LocalDate getDataTarefa() {
        return dataTarefa;
    }

    /**
     * @param dataTarefa the dataTarefa to set
     */
    public void setDataTarefa(LocalDate dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
