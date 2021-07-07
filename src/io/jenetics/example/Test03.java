package io.jenetics.example;

import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.engine.Codec;
import io.jenetics.DoubleGene;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.DoubleRange;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;

import java.util.stream.Stream;

import io.jenetics.Chromosome;

public class Test03 {
    public static void main(String[] args){
        solve();
    }
    public static double evaluate(double[] positions){
        double sum=0;
        for(double i:positions){
            sum+=i*i;
        }
        return sum/positions.length;
    }
    public static void solve(){
        final Engine<DoubleGene,Double> engine= Engine.builder(Test03::evaluate,
                Codecs.ofVector(DoubleRange.of(-100,100),20))
                .optimize(Optimize.MINIMUM).build();
        final EvolutionStatistics<Double, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<DoubleGene, Double> best = engine.stream()
                .limit(3000)
                .peek(statistics)
                .collect(toBestPhenotype());

        Object[] bestPosition = ((Stream<EvolutionResult<DoubleGene, Double>>) best.genotype().chromosome()).toArray();
        double result=best.fitness();
        for (int i = 0; i < bestPosition.length; i++) {
            DoubleGene dg = (DoubleGene) bestPosition[i];
            System.out.print(dg.allele() + "\t");
        }
        System.out.println("\n"+result);

    }
}
