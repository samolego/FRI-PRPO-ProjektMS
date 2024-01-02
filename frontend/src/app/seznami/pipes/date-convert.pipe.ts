import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: "dateConvert",
})
export class DateConvertPipe implements PipeTransform {
    transform(date: string): string {
        return date.replace(/^([0-9]{4})-([0-9]{2})-([0-9]{2})T(.+)/, "$3.$2.$1");
    }
}
