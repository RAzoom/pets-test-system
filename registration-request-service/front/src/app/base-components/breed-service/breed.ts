export class Breed {
    
    breedName!: string;
    identifier!: string;

    constructor(breedName: string, identifier: string) {
        this.breedName = breedName;
        this.identifier = identifier;
    }
}