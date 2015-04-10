package it.unical.mat.andlv.test;

/**
 * Created by Dave on 02/04/2015.
 */
public class BurningCalories {
     public static final String program_part1= "#maxint=100000.\n" +
             "calories_to_burn(1000).\n" +
             "max_time(30).\n" +
             "detected_fact(D, A) :- calories_burnt_when(A, _), #count{T : detected_activity(T, A, C), C > 75} = D.\n" +
             "calories_burnt_when(\"IN_VEHICLE\", 0).\n" +
             "calories_burnt_when(\"ON_BICYCLE\", 1).\n" +
             "calories_burnt_when(\"ON_FOOT\", 2).\n" +
             "calories_burnt(D, A, CBF) :- detected_fact(D, A), calories_burnt_when(A, CB), CBF = CB * D.\n" +
             "total_burnt_calories(SCBF) :- #sum{CBF, D, A : calories_burnt(D, A, CBF)} = SCBF.%}\n" +
             "calories_burnt_per_activity(\"walking\", 10).\n" +
             "calories_burnt_per_activity(\"running\", 30).\n" +
             "calories_burnt_per_activity(\"pushups\", 50).\n" +
             "calories_burnt_per_activity(\"crunches abs\", 50).\n" +
             "remaining_calories_to_burn(RC) :- calories_to_burn(CTB), total_burnt_calories(TBC), CTB > TBC, RC = CTB - TBC.\n" +
             "remaining_calories_to_burn(0) :- calories_to_burn(CTB), total_burnt_calories(TBC), CTB <= TBC.\n" +
             "how_long_max(A, MHL) :- remaining_calories_to_burn(RC), calories_burnt_per_activity(A, TNTB), RC > 0, MHL = RC / TNTB.\n" +
             "how_long(A, S) :- step(S), how_long_max(A, X), S <= X.\n" +
             "how_long(A, X) :- step(S), how_long_max(A, X), S > X.\n" +
             "step(5).\n" +
             "how_long(A, HL) :- how_long(A, HLp), step(S), how_long_max(A, MHL), max_time(TTS), HL = HLp + S, HL <= TTS, HL <= MHL.\n";

    public static final String program_part2="#maxint=1000.\n" +
            "optimize(minimize, time).\n" +
            "activity_to_do(A, HL) | not_activity_to_do(A, HL) :- calories_burnt_per_activity(A, _), how_long(A, HL).\n" +
            "total_calories_activity_to_do(CB) :- #sum{CBA, A : activity_to_do(A, HL), calories_burnt_per_activity(A, TNTB), CBA = TNTB * HL} = CB, #int(CB).\n" +
            "total_time_activity_to_do(TS) :- #sum{HL, A : activity_to_do(A, HL)} = TS, #int(TS).\n" +
            ":- activity_to_do(A, HL1), activity_to_do(A, HL2), HL1 != HL2.\n" +
            ":- remaining_calories_to_burn(RC), total_calories_activity_to_do(CB), RC > CB.\n" +
            ":- remaining_calories_to_burn(RC), total_calories_activity_to_do(CB), CB > RCp100, RCp100 = RC + 100.\n" +
            ":- max_time(MTS), total_time_activity_to_do(TS), MTS < TS.\n" +
            ":~ optimize(minimize, time), activity_to_do(_, HL). [HL:]\n";

    public static final String program="#maxint=100000.\n" +
            "calories_to_burn(1000).\n" +
            "max_time(30).\n" +
            "detected_fact(D, A) :- calories_burnt_when(A, _), #count{T : detected_activity(T, A, C), C > 75} = D.\n" +
            "calories_burnt_when(\"IN_VEHICLE\", 0).\n" +
            "calories_burnt_when(\"ON_BICYCLE\", 1).\n" +
            "calories_burnt_when(\"ON_FOOT\", 2).\n" +
            "calories_burnt(D, A, CBF) :- detected_fact(D, A), calories_burnt_when(A, CB), CBF = CB * D.\n" +
            "total_burnt_calories(SCBF) :- #sum{CBF, D, A : calories_burnt(D, A, CBF)} = SCBF.%}\n" +
            "calories_burnt_per_activity(\"walking\", 10).\n" +
            "calories_burnt_per_activity(\"running\", 30).\n" +
            "calories_burnt_per_activity(\"pushups\", 50).\n" +
            "calories_burnt_per_activity(\"crunches abs\", 50).\n" +
            "remaining_calories_to_burn(RC) :- calories_to_burn(CTB), total_burnt_calories(TBC), CTB > TBC, RC = CTB - TBC.\n" +
            "remaining_calories_to_burn(0) :- calories_to_burn(CTB), total_burnt_calories(TBC), CTB <= TBC.\n" +
            "how_long_max(A, MHL) :- remaining_calories_to_burn(RC), calories_burnt_per_activity(A, TNTB), RC > 0, MHL = RC / TNTB.\n" +
            "how_long(A, S) :- step(S), how_long_max(A, X), S <= X.\n" +
            "how_long(A, X) :- step(S), how_long_max(A, X), S > X.\n" +
            "step(5).\n" +
            "how_long(A, HL) :- how_long(A, HLp), step(S), how_long_max(A, MHL), max_time(TTS), HL = HLp + S, HL <= TTS, HL <= MHL.\n" +
            "\n" +
            "#maxint=1000.\n" +
            "optimize(minimize, time).\n" +
            "activity_to_do(A, HL) | not_activity_to_do(A, HL) :- calories_burnt_per_activity(A, _), how_long(A, HL).\n" +
            "total_calories_activity_to_do(CB) :- #sum{CBA, A : activity_to_do(A, HL), calories_burnt_per_activity(A, TNTB), CBA = TNTB * HL} = CB, #int(CB).\n" +
            "total_time_activity_to_do(TS) :- #sum{HL, A : activity_to_do(A, HL)} = TS, #int(TS).\n" +
            ":- activity_to_do(A, HL1), activity_to_do(A, HL2), HL1 != HL2.\n" +
            ":- remaining_calories_to_burn(RC), total_calories_activity_to_do(CB), RC > CB.\n" +
            ":- remaining_calories_to_burn(RC), total_calories_activity_to_do(CB), CB > RCp100, RCp100 = RC + 100.\n" +
            ":- max_time(MTS), total_time_activity_to_do(TS), MTS < TS.\n" +
            ":~ optimize(minimize, time), activity_to_do(_, HL). [HL:]\n" +
            "\n";
}