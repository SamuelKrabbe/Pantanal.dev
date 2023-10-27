# GitHub Flow

O GitHub Flow é um fluxo de trabalho simplificado, que prioriza a eficiência e a velocidade na entrega de mudanças sem comprometer a estabilidade do software. 

## O que é?

GitHub Flow é um modelo de gerenciamento de branches, criado e promovido pelo GitHub, que foca na simplicidade e eficácia para projetos que evoluem rapidamente. Contrasta com outros modelos, como o Git Flow, que têm uma estrutura de branches mais complexa.

No GitHub Flow, basicamente, há o branch `main` (anteriormente chamado de `master` em muitos repositórios) e feature branches (ou topic branches). A ideia é que se trabalhe na criação de um novo branch para cada feature ou correção, sem se preocupar em classificar se é um hotfix, release ou qualquer outra categoria.

## Como usar?

1. **Qualquer coisa na branch `main` está sempre pronta para ser colocada em produção.** Assim, `main` deve sempre estar em um estado estável e pronto para deploy.

2. **Crie um novo branch para cada nova tarefa ou feature.** Ao iniciar uma nova tarefa (seja ela uma nova funcionalidade, correção ou qualquer outra alteração), crie um novo branch a partir do `main`.

3. **Faça commits para essa branch localmente e regularmente faça push para o branch correspondente no servidor.** Mantenha o trabalho bem segmentado para que cada commit represente uma unidade lógica de trabalho.

4. **Abra uma Pull Request (PR) a qualquer momento.** Uma vez que você tenha um trabalho inicial, pode abrir uma PR. Isso é útil para iniciar uma discussão sobre o seu trabalho antes mesmo de ele estar concluído.

5. **Após a revisão e eventual correção, merge seu branch ao `main`.** Uma vez que a PR tenha sido revisada e o trabalho esteja pronto para ser integrado, faça o merge do seu branch ao `main`.

6. **Uma vez mergeado, faça o deploy imediatamente.** O `main` está sempre pronto para deploy, então, assim que você fizer o merge, você deve garantir que sua mudança seja colocada em produção.

## Vantagens

- **Simplicidade:** Com menos tipos de branches para se preocupar, é mais fácil entender e se adaptar ao fluxo de trabalho.
  
- **Entregas rápidas:** Focando em entregar pequenas mudanças rapidamente, você obtém feedback mais cedo e pode iterar mais rapidamente.
  
- **Integração Contínua:** O GitHub Flow incentiva a prática de CI (Integração Contínua), garantindo que o código no `main` sempre esteja pronto para produção.
  
- **Foco na colaboração:** Ao abrir PRs cedo e frequentemente, a colaboração entre a equipe é incentivada, tornando a revisão do código uma prática padrão e construtiva.

- **Menor complexidade:** Sem a necessidade de gerenciar múltiplos branches de longa duração (como `develop`, `feature`, `release`, `hotfix`), o processo de desenvolvimento e deploy torna-se mais simples e direto.

Em resumo, o GitHub Flow é uma abordagem simplificada e eficaz para gerenciar projetos, especialmente aqueles que precisam de iterações rápidas e constantes.
