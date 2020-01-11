;PROGRAM PODAJĄCY GODZINĘ PO KAŻDYM NACIŚNIĘCIU KLAWISZA RETURN
MINUTE:     DC      INTEGER(60)
HOUR:       DC      INTEGER(3600)
DAY:        DC      INTEGER(86400)
UTC:        DC      INTEGER(7200)
SEPARATOR:  DC      CHAR(':')
TEN:        DC      INTEGER(10)

MAIN:
            XOR     1, 1
CLOCK:
            HALT
            TIME    0
            ADD     0, UTC
            DIV     0, DAY
            LD      0, 8
            DIV     0, HOUR
            CMP     0, TEN
            JGE     H
            OUT     1
H:
            OUT     0
            LD      0, 8
            DIV     0, MINUTE
            COUT    SEPARATOR
            CMP     0, TEN
            JGE     M
            OUT     1
M:
            OUT     0
            COUT    SEPARATOR
            CMP     8, TEN
            JGE     S
            OUT     1
S:
            OUT     8
            COUT    TEN
            JMP     CLOCK