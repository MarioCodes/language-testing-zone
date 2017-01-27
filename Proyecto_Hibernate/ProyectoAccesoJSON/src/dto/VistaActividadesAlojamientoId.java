package dto;
// Generated 27-ene-2017 16:47:00 by Hibernate Tools 4.3.1



/**
 * VistaActividadesAlojamientoId generated by hbm2java
 */
public class VistaActividadesAlojamientoId  implements java.io.Serializable {


     private int idAlojamiento;
     private int idActividad;
     private String nombreAlojamiento;
     private String descripcionAlojamiento;
     private String direccionSocial;
     private String razonSocial;
     private String telefonoContacto;
     private int valoracionAlojamiento;
     private String fechaApertura;
     private Integer numeroHabitaciones;
     private String provincia;
     private String nombreActividad;
     private String descripcionActividad;
     private String diaRealizacion;
     private String diaSemana;
     private String horaInicio;
     private String horaFin;
     private String localizacion;
     private Integer dificultad;
     private String capacidad;
     private String nombreGuia;

    public VistaActividadesAlojamientoId() {
    }

	
    public VistaActividadesAlojamientoId(int idAlojamiento, int idActividad, String nombreAlojamiento, String direccionSocial) {
        this.idAlojamiento = idAlojamiento;
        this.idActividad = idActividad;
        this.nombreAlojamiento = nombreAlojamiento;
        this.direccionSocial = direccionSocial;
    }
    public VistaActividadesAlojamientoId(int idAlojamiento, int idActividad, String nombreAlojamiento, String descripcionAlojamiento, String direccionSocial, String razonSocial, String telefonoContacto, int valoracionAlojamiento, String fechaApertura, Integer numeroHabitaciones, String provincia, String nombreActividad, String descripcionActividad, String diaRealizacion, String diaSemana, String horaInicio, String horaFin, String localizacion, Integer dificultad, String capacidad, String nombreGuia) {
       this.idAlojamiento = idAlojamiento;
       this.idActividad = idActividad;
       this.nombreAlojamiento = nombreAlojamiento;
       this.descripcionAlojamiento = descripcionAlojamiento;
       this.direccionSocial = direccionSocial;
       this.razonSocial = razonSocial;
       this.telefonoContacto = telefonoContacto;
       this.valoracionAlojamiento = valoracionAlojamiento;
       this.fechaApertura = fechaApertura;
       this.numeroHabitaciones = numeroHabitaciones;
       this.provincia = provincia;
       this.nombreActividad = nombreActividad;
       this.descripcionActividad = descripcionActividad;
       this.diaRealizacion = diaRealizacion;
       this.diaSemana = diaSemana;
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.localizacion = localizacion;
       this.dificultad = dificultad;
       this.capacidad = capacidad;
       this.nombreGuia = nombreGuia;
    }
   
    public int getIdAlojamiento() {
        return this.idAlojamiento;
    }
    
    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }
    public int getIdActividad() {
        return this.idActividad;
    }
    
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    public String getNombreAlojamiento() {
        return this.nombreAlojamiento;
    }
    
    public void setNombreAlojamiento(String nombreAlojamiento) {
        this.nombreAlojamiento = nombreAlojamiento;
    }
    public String getDescripcionAlojamiento() {
        return this.descripcionAlojamiento;
    }
    
    public void setDescripcionAlojamiento(String descripcionAlojamiento) {
        this.descripcionAlojamiento = descripcionAlojamiento;
    }
    public String getDireccionSocial() {
        return this.direccionSocial;
    }
    
    public void setDireccionSocial(String direccionSocial) {
        this.direccionSocial = direccionSocial;
    }
    public String getRazonSocial() {
        return this.razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getTelefonoContacto() {
        return this.telefonoContacto;
    }
    
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
    public int getValoracionAlojamiento() {
        return this.valoracionAlojamiento;
    }
    
    public void setValoracionAlojamiento(int valoracionAlojamiento) {
        this.valoracionAlojamiento = valoracionAlojamiento;
    }
    public String getFechaApertura() {
        return this.fechaApertura;
    }
    
    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    public Integer getNumeroHabitaciones() {
        return this.numeroHabitaciones;
    }
    
    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getNombreActividad() {
        return this.nombreActividad;
    }
    
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    public String getDescripcionActividad() {
        return this.descripcionActividad;
    }
    
    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }
    public String getDiaRealizacion() {
        return this.diaRealizacion;
    }
    
    public void setDiaRealizacion(String diaRealizacion) {
        this.diaRealizacion = diaRealizacion;
    }
    public String getDiaSemana() {
        return this.diaSemana;
    }
    
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    public String getHoraInicio() {
        return this.horaInicio;
    }
    
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFin() {
        return this.horaFin;
    }
    
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public String getLocalizacion() {
        return this.localizacion;
    }
    
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
    public Integer getDificultad() {
        return this.dificultad;
    }
    
    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }
    public String getCapacidad() {
        return this.capacidad;
    }
    
    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
    public String getNombreGuia() {
        return this.nombreGuia;
    }
    
    public void setNombreGuia(String nombreGuia) {
        this.nombreGuia = nombreGuia;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VistaActividadesAlojamientoId) ) return false;
		 VistaActividadesAlojamientoId castOther = ( VistaActividadesAlojamientoId ) other; 
         
		 return (this.getIdAlojamiento()==castOther.getIdAlojamiento())
 && (this.getIdActividad()==castOther.getIdActividad())
 && ( (this.getNombreAlojamiento()==castOther.getNombreAlojamiento()) || ( this.getNombreAlojamiento()!=null && castOther.getNombreAlojamiento()!=null && this.getNombreAlojamiento().equals(castOther.getNombreAlojamiento()) ) )
 && ( (this.getDescripcionAlojamiento()==castOther.getDescripcionAlojamiento()) || ( this.getDescripcionAlojamiento()!=null && castOther.getDescripcionAlojamiento()!=null && this.getDescripcionAlojamiento().equals(castOther.getDescripcionAlojamiento()) ) )
 && ( (this.getDireccionSocial()==castOther.getDireccionSocial()) || ( this.getDireccionSocial()!=null && castOther.getDireccionSocial()!=null && this.getDireccionSocial().equals(castOther.getDireccionSocial()) ) )
 && ( (this.getRazonSocial()==castOther.getRazonSocial()) || ( this.getRazonSocial()!=null && castOther.getRazonSocial()!=null && this.getRazonSocial().equals(castOther.getRazonSocial()) ) )
 && ( (this.getTelefonoContacto()==castOther.getTelefonoContacto()) || ( this.getTelefonoContacto()!=null && castOther.getTelefonoContacto()!=null && this.getTelefonoContacto().equals(castOther.getTelefonoContacto()) ) )
 && ( (this.getValoracionAlojamiento()==castOther.getValoracionAlojamiento()))
 && ( (this.getFechaApertura()==castOther.getFechaApertura()) || ( this.getFechaApertura()!=null && castOther.getFechaApertura()!=null && this.getFechaApertura().equals(castOther.getFechaApertura()) ) )
 && ( (this.getNumeroHabitaciones()==castOther.getNumeroHabitaciones()) || ( this.getNumeroHabitaciones()!=null && castOther.getNumeroHabitaciones()!=null && this.getNumeroHabitaciones().equals(castOther.getNumeroHabitaciones()) ) )
 && ( (this.getProvincia()==castOther.getProvincia()) || ( this.getProvincia()!=null && castOther.getProvincia()!=null && this.getProvincia().equals(castOther.getProvincia()) ) )
 && ( (this.getNombreActividad()==castOther.getNombreActividad()) || ( this.getNombreActividad()!=null && castOther.getNombreActividad()!=null && this.getNombreActividad().equals(castOther.getNombreActividad()) ) )
 && ( (this.getDescripcionActividad()==castOther.getDescripcionActividad()) || ( this.getDescripcionActividad()!=null && castOther.getDescripcionActividad()!=null && this.getDescripcionActividad().equals(castOther.getDescripcionActividad()) ) )
 && ( (this.getDiaRealizacion()==castOther.getDiaRealizacion()) || ( this.getDiaRealizacion()!=null && castOther.getDiaRealizacion()!=null && this.getDiaRealizacion().equals(castOther.getDiaRealizacion()) ) )
 && ( (this.getDiaSemana()==castOther.getDiaSemana()) || ( this.getDiaSemana()!=null && castOther.getDiaSemana()!=null && this.getDiaSemana().equals(castOther.getDiaSemana()) ) )
 && ( (this.getHoraInicio()==castOther.getHoraInicio()) || ( this.getHoraInicio()!=null && castOther.getHoraInicio()!=null && this.getHoraInicio().equals(castOther.getHoraInicio()) ) )
 && ( (this.getHoraFin()==castOther.getHoraFin()) || ( this.getHoraFin()!=null && castOther.getHoraFin()!=null && this.getHoraFin().equals(castOther.getHoraFin()) ) )
 && ( (this.getLocalizacion()==castOther.getLocalizacion()) || ( this.getLocalizacion()!=null && castOther.getLocalizacion()!=null && this.getLocalizacion().equals(castOther.getLocalizacion()) ) )
 && ( (this.getDificultad()==castOther.getDificultad()) || ( this.getDificultad()!=null && castOther.getDificultad()!=null && this.getDificultad().equals(castOther.getDificultad()) ) )
 && ( (this.getCapacidad()==castOther.getCapacidad()) || ( this.getCapacidad()!=null && castOther.getCapacidad()!=null && this.getCapacidad().equals(castOther.getCapacidad()) ) )
 && ( (this.getNombreGuia()==castOther.getNombreGuia()) || ( this.getNombreGuia()!=null && castOther.getNombreGuia()!=null && this.getNombreGuia().equals(castOther.getNombreGuia()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdAlojamiento();
         result = 37 * result + this.getIdActividad();
         result = 37 * result + ( getNombreAlojamiento() == null ? 0 : this.getNombreAlojamiento().hashCode() );
         result = 37 * result + ( getDescripcionAlojamiento() == null ? 0 : this.getDescripcionAlojamiento().hashCode() );
         result = 37 * result + ( getDireccionSocial() == null ? 0 : this.getDireccionSocial().hashCode() );
         result = 37 * result + ( getRazonSocial() == null ? 0 : this.getRazonSocial().hashCode() );
         result = 37 * result + ( getTelefonoContacto() == null ? 0 : this.getTelefonoContacto().hashCode() );
         result = 37 * result + ( getFechaApertura() == null ? 0 : this.getFechaApertura().hashCode() );
         result = 37 * result + ( getNumeroHabitaciones() == null ? 0 : this.getNumeroHabitaciones().hashCode() );
         result = 37 * result + ( getProvincia() == null ? 0 : this.getProvincia().hashCode() );
         result = 37 * result + ( getNombreActividad() == null ? 0 : this.getNombreActividad().hashCode() );
         result = 37 * result + ( getDescripcionActividad() == null ? 0 : this.getDescripcionActividad().hashCode() );
         result = 37 * result + ( getDiaRealizacion() == null ? 0 : this.getDiaRealizacion().hashCode() );
         result = 37 * result + ( getDiaSemana() == null ? 0 : this.getDiaSemana().hashCode() );
         result = 37 * result + ( getHoraInicio() == null ? 0 : this.getHoraInicio().hashCode() );
         result = 37 * result + ( getHoraFin() == null ? 0 : this.getHoraFin().hashCode() );
         result = 37 * result + ( getLocalizacion() == null ? 0 : this.getLocalizacion().hashCode() );
         result = 37 * result + ( getDificultad() == null ? 0 : this.getDificultad().hashCode() );
         result = 37 * result + ( getCapacidad() == null ? 0 : this.getCapacidad().hashCode() );
         result = 37 * result + ( getNombreGuia() == null ? 0 : this.getNombreGuia().hashCode() );
         return result;
   }   


}


