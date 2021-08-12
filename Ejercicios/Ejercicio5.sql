El aislamiento es una propiedad que define cómo y cuándo los cambios
producidos por una operación se hacen visibles para las demás operaciones
concurrentes. Aislamiento es una de las 4 propiedades ACID (Atomicidad, 
Consistencia, Aislamiento, Durabilidad).

Al mismo tiempo, solo una transacción puede solicitar los mismos datos,
y las diferentes transacciones no interfieren entre sí. Por ejemplo, en el 
ejercicio de la pizza y los ingredientes, no se podrá leer una pizza 
mientras no se haya terminado la transacción completa con sus ingredientes.

Las transacciones especifican un nivel de aislamiento que define 
el grado en que se debe aislar una transacción de las modificaciones
de recursos o datos realizadas por otras transacciones. 
Los niveles de aislamiento se describen en función de 
los efectos secundarios de la simultaneidad que se permiten, 
como las lecturas de datos sucios o las lecturas fantasmas.

Los niveles de aislamiento de transacciones controlan lo siguiente:
    -Controla si se realizan bloqueos cuando se leen los datos y qué 
    tipos de bloqueos se solicitan.
    -Duración de los bloqueos de lectura.
    -Si una operación de lectura que hace referencia a 
    filas modificadas por otra transacción:
        +Se bloquea hasta que se libera el bloqueo exclusivo de la fila.
        +Recupera la versión confirmada de la fila que 
        existía en el momento en el que se inició la 
        instrucción o la transacción.
        +Lee la modificación de los datos no confirmada.

La selección de un nivel de aislamiento de transacción no 
afecta a los bloqueos adquiridos para proteger las modificaciones 
de datos. Siempre se obtiene un bloqueo exclusivo en los datos 
modificados de una transacción, bloqueo que se mantiene hasta que 
se completa la transacción, independientemente del nivel de aislamiento 
seleccionado para la misma. En el caso de las operaciones de lectura, 
los niveles de aislamiento de transacción definen básicamente el nivel 
de protección contra los efectos de las modificaciones que realizan 
otras transacciones.

Un nivel de aislamiento menor significa que los usuarios tienen un 
mayor acceso a los datos simultáneamente, con lo que aumentan los 
efectos de la simultaneidad que pueden experimentar, como las 
lecturas de datos sucios o la pérdida de actualizaciones. 
Por el contrario, un nivel de aislamiento mayor reduce los 
tipos de efectos de simultaneidad, pero requiere más recursos 
del sistema y aumenta las posibilidades de que una transacción 
bloquee a otra. El nivel de aislamiento apropiado depende del 
equilibrio entre los requisitos de integridad de los datos de 
la aplicación y la sobrecarga de cada nivel de aislamiento. 
El nivel de aislamiento superior, que es serializable, 
garantiza que una transacción recuperará exactamente los mismos 
datos cada vez que repita una operación de lectura, aunque para 
ello aplicará un nivel de bloqueo que puede afectar a los demás 
usuarios en los sistemas multiusuario. El nivel de aislamiento menor, 
de lectura sin confirmar, puede recuperar datos que otras transacciones 
han modificado pero no confirmado. En este nivel se pueden producir 
todos los efectos secundarios de simultaneidad, pero no hay bloqueos 
ni versiones de lectura, por lo que la sobrecarga se reduce.

Las transacciones se deben ejecutar en un nivel de 
aislamiento de lectura repetible, al menos, para 
evitar las pérdidas de actualizaciones que pueden
producirse cuando dos transacciones recuperan la misma 
fila, y a continuación la actualizan según los valores
recuperados originalmente. Si las dos transacciones 
actualizan las filas con una única instrucción UPDATE 
y no basan la actualización en los valores recuperados 
previamente, la pérdida de las actualizaciones no puede 
producirse en el nivel de aislamiento predeterminado de 
lectura confirmada.








